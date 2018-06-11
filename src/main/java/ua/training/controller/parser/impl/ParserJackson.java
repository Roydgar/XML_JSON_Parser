package ua.training.controller.parser.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import ua.training.controller.parser.JSONCurrencyParser;
import ua.training.entity.Currency;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ParserJackson implements JSONCurrencyParser {

    private String url;
    private List<Currency> currencies = new ArrayList<>();

    public ParserJackson(String url) {
        this.url = url;
    }

    public void parse() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            currencies = mapper.readValue(new URL(url), mapper.getTypeFactory().
                    constructCollectionType(List.class, Currency.class));

        } catch (IOException e ){
            e.printStackTrace();
        }
    }

    @Override
    public List<Currency> getResult() {
        return currencies;
    }

}
