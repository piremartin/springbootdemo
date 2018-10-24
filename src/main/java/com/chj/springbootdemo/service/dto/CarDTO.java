package com.chj.springbootdemo.service.dto;

import com.chj.springbootdemo.domain.Person;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CarDTO {

    private String make;
    private int numberOfSeats;
    private Date manufactoringDate;
    private Person driver;
    private List<Person> passengers;

    public CarDTO(){}

    public CarDTO(String make, int numberOfSeats, Date manufactoringDate, Person driver, List<Person> passengers){
        this.make = make;
        this.numberOfSeats = numberOfSeats;
        this.manufactoringDate = manufactoringDate;
        this.driver = driver;
        this.passengers = passengers;
    }
}
