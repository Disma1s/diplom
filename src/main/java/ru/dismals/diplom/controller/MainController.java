package ru.dismals.diplom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dismals.diplom.model.YearAndPrice;

/**
 * @author Yurii Tyshchuk
 */
@RestController
public class MainController {

    public MainController() {
    }

    @GetMapping("/")
    public YearAndPrice getAllYears() {
        return new YearAndPrice();
    }
}
