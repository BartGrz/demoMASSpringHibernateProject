package com.pl.bg.javamasproject.demo.MP1;


import java.io.Serializable;
import java.util.*;

public class Client implements Serializable {

    private int id;
    private String name;
    private String lastName;
    private String phoneNumb;
  //  private Optional<String> emailAdress; //atrybut opcjonalny
    private int count = 0;
    public Set<Pet> pets = new HashSet<>();

    public Client(String name, String lastName, String phoneNumb) { //Optional<String> emailAdress

        this.id = generateId();
        this.name = name;
        this.lastName = lastName;
        this.phoneNumb = phoneNumb;
        //  this.emailAdress = emailAdress;
        count += 1;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumb() {
        return phoneNumb;
    }

    public int getId() {
        return id;
    }

    public int generateId() {

        return count + 1;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumb='" + phoneNumb + '\'' +
                '}';
    }
}

