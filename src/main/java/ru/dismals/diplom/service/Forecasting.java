package ru.dismals.diplom.service;

import com.workday.insights.timeseries.arima.Arima;
import com.workday.insights.timeseries.arima.struct.ArimaParams;
import com.workday.insights.timeseries.arima.struct.ForecastResult;
import org.springframework.stereotype.Service;
import ru.dismals.diplom.model.old.Product;
import ru.dismals.diplom.model.old.YearAndPrice;
import ru.dismals.diplom.repository.ProductRepo;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 *
 */
@Service
public class Forecasting {

    private final ProductRepo productRepo;
    public Map<String, List<Integer>> forecastingResult = new HashMap<>();

    public Forecasting(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @PostConstruct
    public void start() {
        int p = 20;
        int d = 0;
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

            int[] ints = DoubleStream.of(forecastResult.getForecast()).mapToInt(value -> (int) value).toArray();

            forecastingResult.put(
                    product.getNameProduct(),
                    Arrays.stream(ints)
                            .boxed()
                            .collect(Collectors.toList())
            );
            print(forecastResult.getForecast(), product.getNameProduct());
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
