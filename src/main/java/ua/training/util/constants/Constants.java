package ua.training.util.constants;

public interface Constants {
    String XML_FILE_NAME = "catalog.xml";
    String OUTPUT_FILENAME           = "parsingOutput.txt";
    String BANK_URL                  = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    String XPATH_COMPILE_EXPRESSION = "/catalog/notebook/person";

    String CURRENCY_REGEX           = "(USD)|(EUR)|(RUB)";
    Integer CASH_TO_FILTER          = 10000;
}
