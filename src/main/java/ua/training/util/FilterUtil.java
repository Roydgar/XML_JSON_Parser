package ua.training.util;

import ua.training.entity.Currency;
import ua.training.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FilterUtil {
    public static List<Person> filterByCash(List<Person> people, int cash) {
        List<Person> result = new ArrayList<>();
        for (Person person : people) {
            if (person.getCash() > cash) {
                result.add(person);
            }
        }
        return result;
    }

    public static List<Currency> filterByRegex(List<Currency> currencies, String regex) {
        List<Currency> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        for (Currency currency : currencies) {
            if (pattern.matcher(currency.getCc()).matches()) {
                result.add(currency);
            }
        }
        return result;
    }
}
