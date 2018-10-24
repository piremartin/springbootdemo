package com.chj.springbootdemo.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Car {
    private String make;
    private int numberOfSeats;
    private Date manufactoringDate;
    private Person driver;
    private List<Person> passengers;

    public Car(){}

    public Car(String make, int numberOfSeats, Date manufactoringDate, Person driver, List<Person> passengers){
        this.make = make;
        this.numberOfSeats = numberOfSeats;
        this.manufactoringDate = manufactoringDate;
        this.driver = driver;
        this.passengers = passengers;
    }
}
