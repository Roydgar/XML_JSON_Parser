package ua.training.xmlParser;

import org.junit.Before;
import org.junit.Test;
import ua.training.controller.parser.impl.ParserStAX;
import ua.training.entity.Person;
import ua.training.util.FileUtil;
import ua.training.util.constants.Constants;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParserStAXTest {

    private String fileAsString;

    @Before
    public void init() {
        new ParserStAX(Constants.XML_FILE_NAME, Constants.OUTPUT_FILENAME).parse();
        fileAsString = FileUtil.readFromFile(Constants.OUTPUT_FILENAME);
    }

    @Test
    public void containsTest() {
        Person person = new Person("John", "Pravdy street, 23B", 25000);
        assertTrue(fileAsString.contains(person.toString()));
    }

    @Test
    public void testNotContain() {
        Person person = new Person("Bob", "Pyshkinskya street, 12", 0);
        assertFalse(fileAsString.contains(person.toString()));
    }
}
