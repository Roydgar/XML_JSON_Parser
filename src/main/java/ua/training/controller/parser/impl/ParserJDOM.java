package ua.training.controller.parser.impl;

import org.w3c.dom.*;
import ua.training.controller.parser.XMLPersonParser;
import ua.training.util.constants.TagNames;
import ua.training.entity.Person;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParserJDOM implements XMLPersonParser {

    private String fileName;
    private List<Person> result = new ArrayList<>();

    public ParserJDOM(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void parse() {

        try {

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File(fileName));
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(TagNames.PERSON);

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    Person person =  new Person(
                            eElement.getElementsByTagName(TagNames.NAME).item(0).getTextContent(),
                            eElement.getElementsByTagName(TagNames.ADDRESS).item(0).getTextContent(),
                            Long.parseLong(
                                    eElement.getElementsByTagName(TagNames.CASH).item(0).getTextContent())
                    );

                    result.add(person);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Person> getResult() {
        return result;
    }
}
