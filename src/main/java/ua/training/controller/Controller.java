package ua.training.controller;

import ua.training.controller.parser.JSONCurrencyParser;
import ua.training.controller.parser.XMLPersonParser;
import ua.training.controller.parser.impl.ParserStAX;
import ua.training.entity.Currency;
import ua.training.entity.Person;
import ua.training.util.FileUtil;
import ua.training.util.FilterUtil;
import ua.training.util.PrettyPrinterUtil;
import ua.training.util.constants.Constants;

import java.util.List;

public class Controller {

    public Controller() {
    }

    public void runXMLParser(XMLPersonParser parser) {
        parser.parse();

        List<Person> filtered = FilterUtil.filterByCash(parser.getResult(), Constants.CASH_TO_FILTER);
        PrettyPrinterUtil.printListToConsole(filtered);
        FileUtil.writePersonListToFile(filtered, Constants.OUTPUT_FILENAME);
    }

    public void runStAXParser(ParserStAX parserStAX) {
        parserStAX.parse();
    }

    public void runJSONParser(JSONCurrencyParser parser) {
        parser.parse();
        PrettyPrinterUtil.printListToConsole(parser.getResult());
        FileUtil.writeCurrenciesToFile(parser.getResult(), Constants.OUTPUT_FILENAME);
    }

    public void runJSONParser(JSONCurrencyParser parser, String regex) {
        parser.parse();
        List<Currency> filtered = FilterUtil.filterByRegex(parser.getResult(), regex);
        PrettyPrinterUtil.printListToConsole(filtered);
        FileUtil.writeCurrenciesToFile(filtered, Constants.OUTPUT_FILENAME);
    }




}
