package com.pl.bg.javamasproject.demo.MP1;


import lombok.*;
import lombok.experimental.ExtensionMethod;
import net.fortuna.ical4j.model.DateTime;
import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Data
@ToString(exclude ={ "client","getYear","yearOfBirth"})
public class Pet implements Serializable {

    @Getter
    private String name;
    @Getter
    private String species;
    @Getter
    private Client client;
    @Getter
    private int getYear  = LocalDateTime.now().getYear();
    @Getter
    private int yearOfBirth ;
    @Getter
    private int age ;

    public Pet(String name, String species, int yearOfBirth) {
        this.name = name;
        this.species = species;
        this.yearOfBirth = yearOfBirth;
        this.age=howOldIAm();
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
