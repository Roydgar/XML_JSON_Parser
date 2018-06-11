package ua.training.util.constants;

public interface Constants {
    String XML_FILE_NAME = "catalog.xml";
    String OUTPUT_FILENAME           = "parsingOutput.txt";
    String BANK_URL                  = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    String XPATH_COMPILE_EXPRESSION = "/catalog/notebook/person";

    String CURRENCY_REGEX           = "(USD)|(EUR)|(RUB)";
    Integer CASH_TO_FILTER          = 10000;

    String USAGE = "To use program use next commands:\njava parser 'xmlFilePath' - parses xml file\n" +
            "java parser -currency  -   parses current currency rate and writes to parsingOutput.txt\n" +
            "java currency 'currency-list' - parses currency rate to following currency codes\n\t" +
            "(Example: java parser -currency USD RUB EUR).";

    String CURRENCY_PARAMETER       = "-currency";
}
