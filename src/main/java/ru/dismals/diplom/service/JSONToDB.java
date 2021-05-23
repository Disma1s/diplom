package ru.dismals.diplom.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.json.JSONArray;
import org.json.JSONTokener;
import org.springframework.stereotype.Service;
import ru.dismals.diplom.model.newEntity.NewProduct;
import ru.dismals.diplom.model.newEntity.Price;
import ru.dismals.diplom.model.newEntity.Shop;
import ru.dismals.diplom.repository.PriceRepo;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yurii Tyshchuk
 */
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JSONToDB {

    PriceRepo priceRepo;

    @PostConstruct
    public void init() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("target/classes/static/LeMagasin.json"))) {

            JSONArray shopList = new JSONArray(new JSONTokener(bufferedReader));

            List<Price> prices = new ArrayList<>();

            if (!priceRepo.findFirstByOrderById().isPresent()) {
                for (int i = 0; i < shopList.length(); i++) {
                    Shop shop = Shop.builder()
                            .name(shopList.getJSONObject(i).getString("Магазин"))
                            .build();
                    JSONArray products = shopList.getJSONObject(i).getJSONArray("Продукты");

                    for (int k = 0; k < products.length(); k++) {
                        NewProduct newProduct = NewProduct.builder()
                                .name(products.getJSONObject(k).getString("Имя"))
                                .shop(shop)
                                .build();
                        JSONArray priceList = products.getJSONObject(k).getJSONArray("Цены");

                        for (int j = 0; j < priceList.length(); j++) {
                            prices.add(Price.builder()
                                    .price((int) (priceList.getDouble(j)))
                                    .newProduct(newProduct)
                                    .build()
                            );
                        }
                    }
                }
            }
            priceRepo.saveAll(prices);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
