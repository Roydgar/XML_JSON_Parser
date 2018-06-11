package ua.training.jsonParser;

import org.junit.Before;
import org.junit.Test;
import ua.training.controller.parser.impl.ParserGSON;
import ua.training.controller.parser.impl.ParserJackson;
import ua.training.entity.Currency;
import ua.training.util.constants.Constants;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParserGSONTest {
    private List<Currency> currencies;

    @Before
    public void init() {
        ParserGSON parser = new ParserGSON(Constants.BANK_URL);
        parser.parse();
        currencies = parser.getResult();
    }

    @Test
    public void testContains() {
        Currency currency = new Currency("643", "Російський рубль",
                "0.4234", "RUB", "10.06.2018");
        assertTrue(currencies.contains(currency));
    }

    @Test
    public void testNotContain() {
        Currency currency = new Currency("643", "MyCurrency",
                "0.4234", "Any", "10.06.2018");
        assertFalse(currencies.contains(currency));
    }
}
