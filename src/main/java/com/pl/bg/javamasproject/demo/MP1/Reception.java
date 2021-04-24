package com.pl.bg.javamasproject.demo.MP1;


import lombok.NoArgsConstructor;
import net.fortuna.ical4j.model.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


public class Reception implements Serializable {

    private final Logger logger = LoggerFactory.getLogger(Reception.class); //atrybut zlozony
    private final String filepath = System.getProperty("user.home") + "\\savedObject.txt"; //atrybut klasowy
    private static Map<Client, Set<Pet>> map = new HashMap<>(); // ekstensja klasy
    private SaveObject saveObject = new SaveObject();



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

    //przeciazenie
    public static Optional<Client> findClientBy(int id) {

        for (Iterator<Client> it = map.keySet().iterator(); it.hasNext(); ) {
            Client client = it.next();
            if (client.getId() == id) {
                return Optional.of(client);
            }
        }
        return Optional.empty();
    }
    //przeciazenie
    public static Optional<List<Client>> findClientBy(String name) {
            List<Client> list = new ArrayList<>();

        for (Iterator<Client> it = map.keySet().iterator(); it.hasNext(); ) {
            Client client = it.next();

            if (client.getName().equals(name)) {
                list.add(client);
            }
        }

        if(list.isEmpty()) {
            return Optional.empty();
        }else{
            return Optional.of(list);
        }

    }


    public static List<String> filterBySpecies(Enum<Pet.Species> species) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < map.values().size(); i++) {

            String result = map.values()
                    .stream().collect(Collectors.toList()).get(i)
                    .stream()
                    .filter(pet -> pet.getSpecies()
                            .equals(species.toString().toLowerCase()))
                    .map(Pet::getName).collect(Collectors.joining());

            list.add(result);
        }

        return list;
    }

    public void addPetToExistingClient(int id, Pet pet) {
        Client client = null;
        for (Iterator<Client> it = map.keySet().iterator(); it.hasNext(); ) {
            client  = it.next();
            if (client.getId() == id) {
                client.getPets().add(pet);
                logger.warn(pet.toString() + " added to client with id = " + client.getId());
                break;
            }else {

            }
        }

    }
    //metoda klasowa
    public Optional<String> showClientsAndTheirOwnersById(int id) { //atr opcjonalny

        for (Iterator<Client> it = map.keySet().iterator(); it.hasNext(); ) {
            Client client = it.next();
            if (client.getId() == id) {
                return Optional.of(client.getName() + " owner of : " + client.getPets());
            }
        }
        return Optional.empty();
    }
    public void commit(Map map)  {

        saveObject.saveObject(filepath, map);
    }


    @PostConstruct
    public void load() throws IOException {

        if(validateSavedFile(filepath)) {
            map = (Map<Client, Set<Pet>>) loadObject();
        }else {

        }
    }

    public Object loadObject() {

        FileInputStream fileInputStream = null;
        Object obj = null;
        try {
            fileInputStream = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            obj = ois.readObject();
            ois.close();
            logger.info("Object loaded");
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Object could not be loaded " + e);
            map = new HashMap<>();


        }
        return  obj;

    }

    private boolean validateSavedFile(String filepath) throws IOException {
        File file = new File(filepath);

       if(file.length()==0) {
            return false;
        }else {
            return true;
        }

    }
}





