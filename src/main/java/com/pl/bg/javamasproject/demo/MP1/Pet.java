package com.pl.bg.javamasproject.demo.MP1;


import lombok.*;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Data
@ToString(exclude ={ "client","getYear","yearOfBirth"}) //przeciazenie
public class Pet implements Serializable {

    private int id = generateId();
    @Getter
    private String name;
    @Getter
    private String species;
    @Getter
    private Client client;
    @Getter
    private int getYear  = LocalDateTime.now().getYear();
    @Getter
    private int yearOfBirth ; //atrybut opcjonalny
    @Getter
    private int age ; //atrybut wyliczalny
    @Getter
    private Map<Integer,Doctor> medicalCareHIstory = new HashMap<>();

    private static int count = 1;
    private int generateId() {
        return count++;
    }

    public Pet(String name, String species, int yearOfBirth) {
        this.name = name;
        this.species = species;
        this.yearOfBirth = yearOfBirth;
        this.age=howOldIAm();
    }
    public Pet(String name, String species) {
        this.name = name;
        this.species = species;
    }

    //atrybut wyliczalny
    public int howOldIAm() {
        return getYear - yearOfBirth;
    }

    public static String convertFromEnum(Enum<Species> val) {

        return val.toString().toLowerCase();
    }
    enum Species implements Serializable {

        REPTILE,BIRD,AMPHIBIAN,MAMMAL
    }

}
