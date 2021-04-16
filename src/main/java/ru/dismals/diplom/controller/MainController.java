package ru.dismals.diplom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dismals.diplom.repository.ProductRepo;
import ru.dismals.diplom.service.Forecasting;

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


        return "index";
    }
}
