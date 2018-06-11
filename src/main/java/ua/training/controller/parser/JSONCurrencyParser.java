package ua.training.controller.parser;

import ua.training.entity.Currency;

import java.util.List;

public interface JSONCurrencyParser {
    void parse();
    List<Currency> getResult();
}
