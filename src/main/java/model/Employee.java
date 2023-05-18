package model;

import lombok.Data;

@Data

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private City city;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, String gender, int age, City city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.city = city;
    }

    public Employee(String firstName, String lastName, String gender, int age, City city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.city = city;
    }

    @Override
    public String toString() {
        return "id - " + id +
                ", first_name - " + firstName +
                ", last_name - " + lastName +
                ", gender - " + gender +
                ", age - " + age +
                ", city - " + city;
    }
}
