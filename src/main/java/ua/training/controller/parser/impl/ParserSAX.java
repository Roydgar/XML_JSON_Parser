package ua.training.controller.parser.impl;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import ua.training.controller.parser.XMLPersonParser;
import ua.training.util.constants.TagNames;
import ua.training.entity.Person;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ParserSAX extends DefaultHandler implements XMLPersonParser {

    private String fileName;
    private Person person = new Person();
    private String currentElement;
    private List<Person> result = new ArrayList<>();

    public ParserSAX(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        currentElement = qName;

    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase(TagNames.PERSON)) {
            result.add(person);
            person = new Person();
        }

        currentElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentElement.equalsIgnoreCase(TagNames.NAME)) {
            person.setName(new String(ch, start, length));
        }

        if (currentElement.equalsIgnoreCase(TagNames.ADDRESS)) {
            person.setAddress(new String(ch, start, length));
        }

        if (currentElement.equalsIgnoreCase(TagNames.CASH)) {
            person.setCash(Long.parseLong(new String(ch, start, length)));
        }
    }

    @Override
    public void endDocument() { }

    @Override
    public void parse() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            parser.parse(new File(fileName), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Person> getResult() {
        return result;
    }
}
