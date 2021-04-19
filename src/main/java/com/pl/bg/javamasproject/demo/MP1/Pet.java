package com.pl.bg.javamasproject.demo.MP1;


import lombok.*;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@ToString(exclude = "client")
public class Pet implements Serializable {

    @Getter
    private final String name;
    @Getter
    private final String species;
    @Getter
    private  Client client;

    public static String convertFromEnum(Enum val) {

        return val.toString().toLowerCase();
    }
    enum Species implements Serializable {

        REPTILE,BIRD,AMPHIBIAN,MAMMAL
    }
}
