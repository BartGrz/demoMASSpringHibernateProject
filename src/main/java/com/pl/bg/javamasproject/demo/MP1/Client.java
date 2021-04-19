package com.pl.bg.javamasproject.demo.MP1;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;

@Data
@RequiredArgsConstructor
@ToString
public class Client implements Serializable {

    @Getter
    private  final int id = generateId();
    @Getter
    private final String name;
    @Getter
    private final String lastName;
    @Getter
    private final String phoneNumb;
    @Getter
    private Set<Pet> pets = new HashSet<>();

    private static int count = 0;

    public int generateId() {
        count+=1;
        return count;
    }



}

