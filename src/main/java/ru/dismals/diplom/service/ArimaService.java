package ru.dismals.diplom.service;

import com.workday.insights.timeseries.arima.Arima;
import com.workday.insights.timeseries.arima.struct.ArimaParams;
import com.workday.insights.timeseries.arima.struct.ForecastResult;
import org.springframework.stereotype.Service;
import ru.dismals.diplom.model.newEntity.NewProduct;
import ru.dismals.diplom.model.newEntity.Price;
import ru.dismals.diplom.model.newEntity.Shop;
import ru.dismals.diplom.model.old.Product;
import ru.dismals.diplom.model.old.YearAndPrice;
import ru.dismals.diplom.repository.ProductRepo;
import ru.dismals.diplom.repository.ShopRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * @author Yurii Tyshchuk
 */
@Service
public class ArimaService {

    private final ProductRepo productRepo;
    private final ShopRepo shopRepo;

    public ArimaService(ProductRepo productRepo, ShopRepo shopRepo) {
        this.productRepo = productRepo;
        this.shopRepo = shopRepo;
    }

    public Map<String, List<Integer>> Arima(ArimaParams params, int forecastSize, int limit) {
        Map<String, List<Integer>> result = new HashMap<>();

        for (Product product : productRepo.findAll()) {
            List<Double> priceList = new ArrayList<>();
            for (YearAndPrice yearAndPrice : product
                    .getYearList()
                    .stream()
                    .limit(limit)
                    .collect(Collectors.toList())) {
                priceList.add((double) yearAndPrice.getPrice());
            }

            ForecastResult forecastResult = Arima
                    .forecast_arima(
                            priceList.stream().mapToDouble(Double::doubleValue).toArray(),
                            forecastSize,
                            params);

            List<Integer> ints = DoubleStream.of(forecastResult.getForecast())
                    .mapToInt(value -> (int) value)
                    .boxed()
                    .collect(Collectors.toList());

            result.put(product.getNameProduct(), ints);
        }

        result.forEach((s, integers) -> {
            System.out.println("Начало " + s);
            integers.forEach(System.out::println);
            System.out.println("Конец" + s);
        });

        return result;
    }

    public List<Shop> ArimaFromNewValue(ArimaParams params, int forecastSize, int limit) {
        List<Shop> result = new ArrayList<>();

        for (Shop shop : shopRepo.findAll()) {
            List<NewProduct> newProductList = new ArrayList<>();
            for (NewProduct newProduct : shop.getProductList()) {
                List<Double> doubleList = new ArrayList<>();
                List<Price> priceList;

                if (limit > 0) {
                    priceList = newProduct.getPrices().subList(0, limit);
                } else {
                    priceList = newProduct.getPrices();
                }

                priceList.forEach(price -> {
                    doubleList.add((double) price.getPrice());
                });

                ForecastResult forecastResult = Arima
                        .forecast_arima(
                                doubleList.stream()
                                        .mapToDouble(value -> value)
                                        .toArray(),
                                forecastSize,
                                params);

                List<Integer> ints = DoubleStream.of(forecastResult.getForecast())
                        .mapToInt(value -> (int) value)
                        .boxed()
                        .collect(Collectors.toList());

                ints.forEach(integer -> {
                    Price price = Price.builder()
                            .price(integer)
                            .newProduct(newProduct)
                            .build();
                    priceList.add(price);
                });

                newProduct.setPrices(priceList);
                newProductList.add(newProduct);
            }
            shop.setProductList(newProductList);
            result.add(shop);
        }
        return result;
    }
}
