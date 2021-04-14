package ru.dismals.diplom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dismals.diplom.model.Product;
import ru.dismals.diplom.model.Year;

import java.util.ArrayList;

/**
 * @author Yurii Tyshchuk
 */
@RestController
public class MainController {

    public MainController() {
    }

    @GetMapping("/")
    public Year getAllYears() {
        return Year.builder()
                .year(2012)
                .productList(new ArrayList<Product>() {
                    {
                        add(Product.builder()
                                .nameProduct("Tea")
                                .price(14)
                                .build()
                        );
                    }
                })
                .build();
    }
}
