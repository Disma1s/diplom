package ru.dismals.diplom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dismals.diplom.model.Product;
import ru.dismals.diplom.repository.ProductRepo;

import java.util.List;

/**
 * @author Yurii Tyshchuk
 */
@RestController
public class MainController {
    private final ProductRepo product;
    public MainController(ProductRepo product) {
        this.product = product;
    }

    @GetMapping("/")
    public List<Product> getAllYears() {
        return product.findAll();
    }
}
