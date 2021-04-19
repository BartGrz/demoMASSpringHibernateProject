package com.pl.bg.javamasproject.demo.MP1;



import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.Serializable;
import java.util.*;

@Data
@RequiredArgsConstructor
public class Client implements Serializable {

    private  final int id = generateId();
    private final String name;
    private final String lastName;
    private final String phoneNumb;

    private static int count = 0;
    public Set<Pet> pets = new HashSet<>();

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
        count+=1;
        return count;
    }

    public Set<Pet> getPets() {
        return pets;
    }


    @Override
    public String toString() {
        return "id="+ id+" ,name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumb='" + phoneNumb + '\'' +
                '}';
    }
}

