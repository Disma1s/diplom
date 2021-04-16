package ru.dismals.diplom.service;

import com.workday.insights.timeseries.arima.Arima;
import com.workday.insights.timeseries.arima.struct.ArimaParams;
import com.workday.insights.timeseries.arima.struct.ForecastResult;
import org.springframework.stereotype.Service;
import ru.dismals.diplom.model.Product;
import ru.dismals.diplom.model.YearAndPrice;
import ru.dismals.diplom.repository.ProductRepo;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class Forecasting {

    private final ProductRepo productRepo;
    private final Parser parser;
    public double[] forecastData;

    public Forecasting(ProductRepo productRepo, Parser parser) {
        this.productRepo = productRepo;
        this.parser = parser;
    }

    @PostConstruct
    public void start() {
        parser.init();
        int p = 20;
        int d = 1;
        int q = 1;
        int P = 0;
        int D = 0;
        int Q = 0;
        int m = 0;
        int forecastSize = 5;

        for (Product product : productRepo.findAll()) {
            List<Double> integerList = new ArrayList<>();

            for (YearAndPrice yearAndPrice : product
                    .getYearList()
                    .stream()
                    .limit(163)
                    .collect(Collectors.toList())) {

                integerList.add((double) yearAndPrice.getPrice());
            }

            ForecastResult forecastResult = Arima
                    .forecast_arima(integerList.stream().mapToDouble(value -> value).toArray(), forecastSize, new ArimaParams(p, d, q, P, D, Q, m));

            forecastData = forecastResult.getForecast();
//            double[] uppers = forecastResult.getForecastUpperConf();
//            double[] lowers = forecastResult.getForecastLowerConf();
//            double rmse = forecastResult.getRMSE();
//            double maxNormalizedVariance = forecastResult.getMaxNormalizedVariance();
            print(forecastData, product.getNameProduct());
        }
    }

    private static void print(double[] doubles, String name) {
        System.out.println("Начало " + name);
        for (int i = 0; i < doubles.length; i++) {
            System.out.println(doubles[i]);
        }
        System.out.println("Конец");
    }
}
