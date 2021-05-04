package com.pl.bg.javamasproject.demo.controllers;

import com.pl.bg.javamasproject.demo.models.ClientRepository;
import com.pl.bg.javamasproject.demo.models.Patient;


import com.pl.bg.javamasproject.demo.models.PatientRepository;
import javassist.NotFoundException;
import org.hibernate.annotations.NotFound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;


class PatientControllerTest {

    @Test
    void updateRecord() {

       PatientRepository repo = mock(com.pl.bg.javamasproject.demo.models.PatientRepository.class);
       Patient patient = mock(Patient.class);
       if(repo.existById(40)){

           System.out.println("404 not found");

       }else {
           repo.findById(anyInt()).ifPresent(updateFrom-> patient.updateFrom(updateFrom));
           System.out.println("200 ok");
       }
    }

    @Test
    void deleteRecord() {

        PatientRepository repo = mock(com.pl.bg.javamasproject.demo.models.PatientRepository.class);

        if (repo.existById(80)) {

            System.out.println("404 not found");

        } else {
            repo.deleteById(7);
        }
    }

    @Test
    void showRecords() {


    }

    @Test
    void addRecord() {


    }

    @Test
    void testSqlRepo() {

        ClientRepository repo = mock(ClientRepository.class);
        ClientController clientController = new ClientController(repo);
       clientController.showRecords();

    }
}