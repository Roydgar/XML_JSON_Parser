package ua.training.controller.parser.impl;

import ua.training.controller.parser.XMLPersonParser;
import ua.training.util.constants.Constants;
import ua.training.util.constants.TagNames;
import ua.training.entity.Person;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class ParserXPath implements XMLPersonParser {

    private String fileName;
    private List<Person> result = new ArrayList<>();

    public ParserXPath(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void parse() {
        try {
            File inputFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath =  XPathFactory.newInstance().newXPath();

            NodeList nodeList = (NodeList) xPath.compile(Constants.XPATH_COMPILE_EXPRESSION).
                    evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    result.add(new Person(
                            eElement.getElementsByTagName(TagNames.NAME).item(0).getTextContent(),
                            eElement.getElementsByTagName(TagNames.ADDRESS).item(0).getTextContent(),
                            Long.parseLong(
                                    eElement.getElementsByTagName(TagNames.CASH).item(0).getTextContent()
                            )));
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
