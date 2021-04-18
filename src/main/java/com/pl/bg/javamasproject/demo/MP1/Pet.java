package com.pl.bg.javamasproject.demo.MP1;


import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

public class Pet extends Animal implements Serializable { //ekstensja

    private String name;
    private Client client;
    private String str;

    public Pet(Species species,  String name) {
        super(species);
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        return super.toString() +
                "name=" + name ;
    }
}
