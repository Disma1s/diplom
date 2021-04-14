package ru.dismals.diplom.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Yurii Tyshchuk
 */
@Service
public class Parser {

    public Parser() {
    }

    @PostConstruct
    private void init() {
        System.out.println(123);
    }
}
