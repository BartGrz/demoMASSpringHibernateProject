package com.pl.bg.javamasproject.demo.MP1;

import javafx.fxml.Initializable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


public class Reception implements Serializable {

    private final String filepath = System.getProperty("user.home") + "\\savedObject.txt";
    private static Map<Client, Set<Pet>> map = new HashMap<>(); //atrybut powt
    SaveOrRead saveOrRead = new SaveOrRead();
    //FIXME nalezy dodac metode load ktora wczyta z pliku zserializowane objekty typu CLient i Pet

    public void addClientToSystem(Client client, Pet pet) throws IOException {

        client.getPets().add(pet);
        map.put(client, client.getPets());
        commit(map);
    }

    public static List<String> showPetsFromSystem() {

        List<String> listOfPets = new ArrayList<>();

        for (Iterator<Set<Pet>> it = map.values().iterator(); it.hasNext(); ) {

            for (int i = 0; i < map.values().size(); i++) {

                listOfPets.add(it.next().stream().collect(Collectors.toList()).toString());
            }

        }

        return listOfPets;
    }

    public static List<String> showClientsFromSystem() {
        List<String> clients = new ArrayList<>();
        for (Iterator<Client> it = map.keySet().iterator(); it.hasNext(); ) {
            clients.add(it.next().toString());
        }
        return clients;
    }

    public static Optional<Client> findClientById(int id) {

        for (Iterator<Client> it = map.keySet().iterator(); it.hasNext(); ) {
            Client client = it.next();
            if (client.getId() == id) {
                return Optional.of(client);
            }
        }
        return Optional.empty();
    }

    public static List<String> filterBySpecies(Enum<Species> species) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < map.values().size(); i++) {
            String result = map.values()
                    .stream().collect(Collectors.toList()).get(i)
                    .stream()
                    .filter(pet -> pet.getSpecies()
                            .equals(species.toString().toLowerCase()))
                    .map(Pet::getName).collect(Collectors.joining(""));

            list.add(result);
        }

        return list;
    }

    public void addPetToExistingClient(int id, Pet pet) {
        for (Iterator<Client> it = map.keySet().iterator(); it.hasNext(); ) {
            Client client = it.next();
            if (client.getId() == id) {
                client.pets.add(pet);
            } else {
                System.out.println("there is no client with given id =" + id);
            }
        }
    }

    public void commit(Map map) throws IOException {

        saveOrRead.saveObject(filepath, map);
    }


    @PostConstruct
    public void load() {

            map = (Map<Client, Set<Pet>>) saveOrRead.loadObject(filepath);
    }
}





