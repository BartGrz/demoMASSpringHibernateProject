package com.pl.bg.javamasproject.demo.controllers;

import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.models.ClientRepository;
import com.pl.bg.javamasproject.demo.models.Patient;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class ClientController implements ControllerTemplate{

    private final Logger log = LoggerFactory.getLogger(ClientController.class);

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }



    @Override
    public void addRecord() {

    }

    @Override
    public void deleteRecord() {

    }

    @Override
    public void showRecords() {

       // clientRepository.findById(1);
    }

    @Override
    public void updateRecord() {

    }

    @Override
    public void start(Stage stage) throws IOException {

    }


    public void show() {

        List<Client> list = clientRepository.findAll();
        List<String> listPa = clientRepository.findAllPatientsNames();
        List<Patient> listPatients = clientRepository.findAllPatients();
        log.warn(""+listPatients);

        clientRepository.save(Client.builder().client_name("Tomasz").client_number(3).build());

       // log.warn(""+check);


    }
}
