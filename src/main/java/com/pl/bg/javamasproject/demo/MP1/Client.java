package com.pl.bg.javamasproject.demo.MP1;

import lombok.*;

import java.io.Serializable;
import java.util.*;


@ToString(exclude = {"pets"})
public class Client implements Serializable {

    @Getter
    private final int id = generateId();
    @Getter
    private String name;
    @Getter
    private String lastName;
    @Getter
    private String phoneNumb;
    @Getter
    private Set<Pet> pets = new HashSet<>(); //atrybut powtarzalny
    @Getter
    private static List<Client> clientsExt = new ArrayList<>(); //ekstensja
    private static int count = 0;

    public Client(String name, String lastName, String phoneNumb) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumb = phoneNumb;
        clientsExt.add(this);
    }

    public int generateId() {
        count += 1;
        return count;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        Client client = new Client("jan", "janusz", "602555444"); //Optional.of("janjanusz@gmail")
        Client client_2 = new Client("Andrzej", "Nowak", "192554887");
        Client client_3 = new Client("Tomasz", "Zawada", "669112364");

        System.out.println(Client.clientsExt);
    }

}

