package ru.dismals.diplom.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ru.dismals.diplom.model.Product;
import ru.dismals.diplom.model.YearAndPrice;
import ru.dismals.diplom.repository.ProductRepo;
import ru.dismals.diplom.repository.YearRepo;

import javax.annotation.PostConstruct;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Yurii Tyshchuk
 */
@Service
public class Parser {
    private final String BASE_URL = "https://ourworldindata.org/grapher/data/variables/" +
            "8841+8840+8838+8837+8834+8833+8815+8814+8813+8812+8811+8807+8804+8806.json?v=2";

    public Parser(ProductRepo productRepo, YearRepo yearRepo) {
    }

    @PostConstruct
    private void init() throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        InputStream inputStream = connection.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        JSONObject jsonObject = new JSONObject(bufferedInputStream);
        JSONObject variables = jsonObject.getJSONObject("variables");

        List<YearAndPrice> yearList = new ArrayList<>();

        Iterator<String> keys = variables.keys();
        while (keys.hasNext()) {
            JSONObject Number = variables.getJSONObject(keys.next());

            String name = Number.getJSONObject("name").toString();
            JSONArray valuesArray = Number.getJSONArray("values");
            JSONArray yearsArray = Number.getJSONArray("years");

            List<Product> productList = new ArrayList<>();

            for (int i = 0; i < yearsArray.length(); i++) {

            }
            
            YearAndPrice year = YearAndPrice.builder()
                    .year(Integer.parseInt(name))
                    .build();

        }
    }
}
