package ua.training;

import ua.training.controller.Controller;
import ua.training.controller.parser.impl.ParserGSON;
import ua.training.controller.parser.impl.ParserJDOM;
import ua.training.controller.parser.impl.ParserStAX;
import ua.training.util.constants.Constants;

import java.nio.file.NoSuchFileException;

public class Parser {
    public static void main(String[] args){
        if (args.length == 0){
            System.out.println(Constants.USAGE);
            return;
        }

        if (args.length == 1 && !args[0].equals(Constants.CURRENCY_PARAMETER)) {
            new Controller().runStAXParser(new ParserStAX(args[0], Constants.OUTPUT_FILENAME));
            return;
        }


        if (args.length == 1 && args[0].equals(Constants.CURRENCY_PARAMETER)) {
            new Controller().runJSONParser(new ParserGSON(Constants.BANK_URL));
            return;
        }

        if (args.length > 1 && args[0].equals(Constants.CURRENCY_PARAMETER)) {
            new Controller().runJSONParser(new ParserGSON(Constants.BANK_URL),
                    createCurrencyRegexFromArgs(args));
            return;
        }

        System.out.println(Constants.USAGE);

    }

    private static String createCurrencyRegexFromArgs(String[] args) {
        StringBuilder regex = new StringBuilder();

        for (int i = 1; i < args.length; i++) {
            regex.append('(').append(args[i]).append(')').append('|');
        }

        regex.replace(regex.length() -1, regex.length(), "");
        return regex.toString();
    }
}
