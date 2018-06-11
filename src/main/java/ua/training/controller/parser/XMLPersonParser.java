package ua.training.controller.parser;

import ua.training.entity.Person;

import java.util.List;

public interface XMLPersonParser {
    void parse();
    List<Person> getResult();
}
