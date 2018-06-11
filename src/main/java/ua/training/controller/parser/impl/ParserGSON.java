package ua.training.controller.parser.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ua.training.controller.parser.JSONCurrencyParser;
import ua.training.entity.Currency;
import ua.training.util.constants.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParserGSON implements JSONCurrencyParser {
    private String url;
    private List<Currency> currencies = new ArrayList<>();

    public ParserGSON(String url) {
        this.url = url;
    }

    @Override
    public void parse() {
        try (Reader reader = new InputStreamReader(new URL(url).openStream(), StandardCharsets.UTF_8)) {

            Type listType = new TypeToken<List<Currency>>() {}.getType();
            currencies = new Gson().fromJson(reader, listType);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Currency> getResult() {
        return currencies;
    }

    public static void main(String[] args) {
        new ParserGSON(Constants.BANK_URL).parse();
    }
}
