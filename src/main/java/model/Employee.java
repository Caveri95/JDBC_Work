package model;

import lombok.Data;

@Data

public class Employee {

    private int id;
    private String first_name;
    private String last_name;
    private String gender;
    private int age;
    private City city;

    public Employee() {
    }

    public Employee(int id, String first_name, String last_name, String gender, int age, City city) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.age = age;
        this.city = city;
    }

    public Employee(String first_name, String last_name, String gender, int age, City city) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.age = age;
        this.city = city;
    }

    @Override
    public String toString() {
        return "id - " + id +
                ", first_name - " + first_name +
                ", last_name - " + last_name +
                ", gender - " + gender +
                ", age - " + age +
                ", city - " + city;
    }
}
