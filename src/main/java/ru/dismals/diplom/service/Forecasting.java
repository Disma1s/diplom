package ru.dismals.diplom.service;

import com.workday.insights.timeseries.arima.Arima;
import com.workday.insights.timeseries.arima.struct.ArimaParams;
import com.workday.insights.timeseries.arima.struct.ForecastResult;
import org.springframework.stereotype.Service;
import ru.dismals.diplom.model.YearAndPrice;
import ru.dismals.diplom.repository.ProductRepo;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class Forecasting {

    private final ProductRepo productRepo;
    private final Parser parser;
    public Forecasting(ProductRepo productRepo, Parser parser) {
        this.productRepo = productRepo;
        this.parser = parser;
    }

    @PostConstruct
    public void start() {
        parser.init();
        int p = 3;
        int d = 0;
        int q = 3;
        int P = 1;
        int D = 1;
        int Q = 0;
        int m = 0;
        int forecastSize = 5;

        List<Double> integerList = new ArrayList<>();

        for (YearAndPrice yearAndPrice : productRepo.findByNameProduct("Sugar")
                .orElseThrow(NoSuchElementException::new)
                .getYearList()
                .stream()
                .limit(161)
                .collect(Collectors.toList())) {

            integerList.add((double) yearAndPrice.getPrice());
        }

        ForecastResult forecastResult = Arima
                .forecast_arima(integerList.stream().mapToDouble(value -> value).toArray(), forecastSize, new ArimaParams(p, d, q, P, D, Q, m));

        double[] forecastData = forecastResult.getForecast();
        double[] uppers = forecastResult.getForecastUpperConf();
        double[] lowers = forecastResult.getForecastLowerConf();
        double rmse = forecastResult.getRMSE();
        double maxNormalizedVariance = forecastResult.getMaxNormalizedVariance();

        print(forecastData);
        print(uppers);
        print(lowers);

        System.out.println(rmse);
        System.out.println("");
        System.out.println(maxNormalizedVariance);

    }

    private static void print(double[] doubles) {
        System.out.println("Начало");
        for (int i = 0; i < doubles.length; i++) {
            System.out.println(doubles[i]);
        }
        System.out.println("Конец");
    }
}
