package com.bestapp.hibernate.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "employee")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private int age;

    @ManyToOne
    @JoinColumn(name = "city_id")
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
