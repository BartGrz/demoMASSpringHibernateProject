package com.pl.bg.javamasproject.demo.MP1;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.pl.bg.javamasproject.demo.MP1.Pet.convertFromEnum;
import static org.junit.jupiter.api.Assertions.*;

class ReceptionTest {

    @Test
    void showPetsFromSystem() {
        Map<Client,Set<Pet>> map = new HashMap<>();
        Client client = new Client("Tomasz","Zawada","669112364");
       // Pet pet = new Pet(convertFromEnum(Pet.Species.MAMMAL),"kropka");
       // client.getPets().add(pet);
        map.put(client,client.getPets());
        List<Pet> pets = new ArrayList<>();

        System.out.println(map.keySet().stream().map(Client::getPets).collect(Collectors.toList()));


    }
}