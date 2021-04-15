package ru.dismals.diplom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dismals.diplom.model.Product;
import ru.dismals.diplom.repository.ProductRepo;

import java.util.List;

/**
 * @author Yurii Tyshchuk
 */
@Controller
public class MainController {
    private final ProductRepo product;
    public MainController(ProductRepo product) {
        this.product = product;
    }

    @GetMapping("/")
    public String getAllYears(Model model) {
        return "index";
    }
}
