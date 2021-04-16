package ru.dismals.diplom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dismals.diplom.model.Product;
import ru.dismals.diplom.model.YearAndPrice;
import ru.dismals.diplom.repository.ProductRepo;
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

    public MainController(ProductRepo product, Forecasting forecasting) {
        this.product = product;
        this.forecasting = forecasting;
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
}
