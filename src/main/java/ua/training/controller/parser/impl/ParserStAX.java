package ua.training.controller.parser.impl;

import ua.training.controller.parser.XMLPersonParser;
import ua.training.entity.Person;
import ua.training.util.FileUtil;
import ua.training.util.constants.Constants;
import ua.training.util.constants.TagNames;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/*
Optimal for parsing big files.
Proceed file parsing line-by-line and writes in to the file one person at one time.
 */

public class ParserStAX implements XMLPersonParser {

    private String xmlFileName;
    private String outputFileName;
    private Person person = new Person();

    public ParserStAX(String xmlFileName, String outputFileName) {
        this.xmlFileName = xmlFileName;
        this.outputFileName = outputFileName;
    }

    @Override
    public void parse() {
        try (StaxStreamProcessor processor = new StaxStreamProcessor(Files.newInputStream(Paths.get(xmlFileName)));
             Writer writer = new BufferedWriter(new FileWriter(outputFileName))) {
            XMLStreamReader reader = processor.getReader();

            while (reader.hasNext()) {       // while not end of XML
                int event = reader.next();   // read next event

                if (event == XMLEvent.START_ELEMENT &&
                        reader.getLocalName().equals(TagNames.PERSON)) {
                    person = new Person();
                }

                if (event == XMLEvent.START_ELEMENT &&
                        reader.getLocalName().equals(TagNames.NAME)) {
                    person.setName(reader.getElementText());
                }

                if (event == XMLEvent.START_ELEMENT &&
                        reader.getLocalName().equals(TagNames.ADDRESS)) {
                    person.setAddress(reader.getElementText());
                }

                if (event == XMLEvent.START_ELEMENT &&
                        reader.getLocalName().equals(TagNames.CASH)) {
                    person.setCash(Long.parseLong(reader.getElementText()));
                }

                if (event == XMLEvent.END_ELEMENT &&
                        reader.getLocalName().equals(TagNames.PERSON)) {
                    writer.append(person.toString()).append('\n');
                }

            }
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> getResult() {
        throw new UnsupportedOperationException("Result goes only to a file");
    }

    static class StaxStreamProcessor implements AutoCloseable {
        private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();

        private final XMLStreamReader reader;

        public StaxStreamProcessor(InputStream is) throws XMLStreamException {
            reader = FACTORY.createXMLStreamReader(is);
        }

        public XMLStreamReader getReader() {
            return reader;
        }

        @Override
        public void close() {
            if (reader != null) {
                try {
                    reader.close();
                } catch (XMLStreamException e) { // empty
                }
            }
        }
    }

    public static void main(String[] args) {
        new ParserStAX(Constants.XML_FILE_NAME, Constants.OUTPUT_FILENAME).parse();
    }
}
