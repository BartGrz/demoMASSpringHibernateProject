package com.pl.bg.javamasproject.demo.MP1;

import lombok.ToString;

@ToString
public class Employee {

    private String firstName;
    private String lastName;
    private String PESEL;
    private Enum<Contract> contract;

    public Employee(String firstName, String lastName, String PESEL, Enum<Contract> contract) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.PESEL = PESEL;
        this.contract = contract;
    }

    public enum Contract {
        EMPLOYMENT,B2B,MANDATE
    }
}
