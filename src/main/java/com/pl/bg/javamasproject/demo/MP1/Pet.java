package com.pl.bg.javamasproject.demo.MP1;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class Pet implements Serializable { //ekstensja

    private final String name;
    private final String species;
    private  Client client;

    public String getName() {
        return name;
    }

    public Client getClient() {
        return client;
    }

    public String getSpecies() {
        return species;
    }

    @Override
    public String toString() {
        return "name=" + name  + " species =" + species;
    }
    public static String convertFromEnum(Enum val) {

        return val.toString().toLowerCase();
    }
    enum Species implements Serializable {

        REPTILE,BIRD,AMPHIBIAN,MAMMAL
    }
}
