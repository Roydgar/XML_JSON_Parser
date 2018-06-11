package ua.training.util;

import java.util.List;

public class PrettyPrinterUtil {
    public static <T> void printListToConsole(List<T> list) {
        list.forEach(System.out::println);
    }
}
