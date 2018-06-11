package ua.training.util;

import ua.training.entity.Currency;
import ua.training.entity.Person;
import ua.training.util.constants.Constants;

import java.io.*;
import java.util.List;
import java.util.regex.Pattern;

public class FileUtil {

    public static void writePersonListToFile(List<Person> people, String outputFileName) {
        try (Writer writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (Person person : people) {
                writer.append(person.toString()).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCurrenciesToFile(List<Currency> currencies, String outputFileName) {
        try (Writer writer = new BufferedWriter(new FileWriter(outputFileName))){
            for (Currency currency : currencies) {
                writer.append(currency.toString()).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader  reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}

