package ru.dismals.diplom.controller;

import com.workday.insights.timeseries.arima.struct.ArimaParams;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dismals.diplom.model.newEntity.Price;
import ru.dismals.diplom.model.newEntity.Shop;
import ru.dismals.diplom.model.old.Product;
import ru.dismals.diplom.model.old.YearAndPrice;
import ru.dismals.diplom.repository.ProductRepo;
import ru.dismals.diplom.service.ArimaService;
import ru.dismals.diplom.service.Forecasting;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yurii Tyshchuk
 */
@Controller
public class MainController {
    private final ProductRepo product;
    private final Forecasting forecasting;
    private final ArimaService arimaService;

    public MainController(ProductRepo product, Forecasting forecasting, ArimaService arimaService) {
        this.product = product;
        this.forecasting = forecasting;
        this.arimaService = arimaService;
    }

    @GetMapping("/")
    public String getAllYears(Model model) {
        for (Product product : product.findAll()) {
            List<Integer> price = product
                    .getYearList().stream()
                    .skip(151)
//                    .limit(163)
                    .map(YearAndPrice::getPrice)
                    .collect(Collectors.toList());

            price.addAll(forecasting.forecastingResult.get(product.getNameProduct()));

            model.addAttribute(product.getNameProduct(), price);
        }

        List<Integer> years = product.findByNameProduct("Sugar")
                .orElse(new Product())
                .getYearList().stream()
                .map(YearAndPrice::getYear)
                .collect(Collectors.toList());

        model.addAttribute("Years", years);
        return "index";
    }

    @GetMapping("/NewValue")
    public String getNewValue(Model model) {
        final List<Shop> shopList = arimaService.ArimaFromNewValue(
                new ArimaParams(2, 0, 1, 0, 0, 0, 0),
                2,
                0
        );

        shopList.forEach(shop -> {
            shop.getProductList().forEach(newProduct -> {
                model.addAttribute(
                        shop.getName() + newProduct.getName(),
                        newProduct.getPrices().stream()
                                .map(Price::getPrice)
                                .collect(Collectors.toList())
                );
            });
        });

        return "NewValue";
    }
}
