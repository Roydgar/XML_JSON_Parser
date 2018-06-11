package ua.training.entity;

import java.util.Objects;

public class Person {
    private String name;
    private String address;
    private long cash;

    public Person() {}

    public Person(String name, String address, long cash) {
        this.name = name;
        this.address = address;
        this.cash = cash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCash() {
        return cash;
    }

    public void setCash(long cash) {
        this.cash = cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(address, person.address) &&
                Objects.equals(cash, person.cash);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, address, cash);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", cash='" + cash + '\'' +
                '}';
    }
}
