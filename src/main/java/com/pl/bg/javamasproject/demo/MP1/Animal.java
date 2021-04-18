package com.pl.bg.javamasproject.demo.MP1;

import java.io.Serializable;

public class Animal implements Serializable {


    private String species;

    public Animal(Enum<Species> species) {
        this.species = species.toString().toLowerCase(); //atrybut zlozony
    }


    @Override
    public String toString() {
        return "species=" + species+",";
    }

    public String getSpecies() {
        return species;
    }

}
enum Species implements Serializable {

    REPTILE,BIRD,AMPHIBIAN,MAMMAL
}