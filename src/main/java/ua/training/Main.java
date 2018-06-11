package ua.training;

import ua.training.controller.Controller;
import ua.training.controller.parser.impl.ParserJDOM;
import ua.training.util.constants.Constants;

public class Main {
    public static void main(String[] args){
        new Controller().runXMLParser(new ParserJDOM(Constants.XML_FILE_NAME));
    }
}
