package ua.training.xmlParser;

import org.junit.Before;
import org.junit.Test;
import ua.training.controller.parser.impl.ParserJDOM;
import ua.training.controller.parser.impl.ParserSAX;
import ua.training.entity.Person;
import ua.training.util.FileUtil;
import ua.training.util.constants.Constants;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParserSAXTest {

    private List<Person> people;

    @Before
    public void init() {
        ParserSAX parser = new ParserSAX(Constants.XML_FILE_NAME);
        parser.parse();
        people = parser.getResult();
    }

    @Test
    public void testContains() {
        Person person = new Person("John", "Pravdy street, 23B", 25000);
        assertTrue(people.contains(person));
    }

    @Test
    public void testNotContain() {
        Person person = new Person("Bob", "Pyshkinskya street, 12", 0);
        assertFalse(people.contains(person));
    }

}
