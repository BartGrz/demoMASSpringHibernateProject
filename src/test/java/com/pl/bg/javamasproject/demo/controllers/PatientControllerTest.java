package com.pl.bg.javamasproject.demo.controllers;




import com.pl.bg.javamasproject.demo.SQL.InsertQueryBuilder;
import com.pl.bg.javamasproject.demo.SQL.Repository;

import com.pl.bg.javamasproject.demo.SQL.TestBul;
import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.models.ClientRepository;
import com.pl.bg.javamasproject.demo.models.Patient;

import com.pl.bg.javamasproject.demo.models.PatientRepository;
import com.pl.bg.javamasproject.demo.tools.Looper;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.mockito.Mockito.mock;


class PatientControllerTest {

    @Test
    void updateRecord() {


    }

    @Test
    void deleteRecord() {

        new Repository<Patient>().delete(new Patient(), 4);

    }

    @Test
    void showRecords() {


    }

    @Test
    void addRecord() {


        new InsertQueryBuilder.Builder<Patient>()
                .insertInto(Patient.class)
                .fields(Patient.getListOfTableFields())
                .value("kropka")
                .value(1)
                .value(1)
                .end()
                .generateAndExecuteSQL();


    }

    /*
    @Test
    void testShowRecords() {

        PatientService patientService = new PatientService();

        List<Patient> list = TestBul.<Patient,Class>builder().build().generateBasicSelectResult(Patient.class);
        List<Patient> list_2 = patientService.findAll();
        System.out.println(list);
    }

     */
    @Test
    void testSqlRepo() {

        ClientRepository repo = mock(ClientRepository.class);
        ClientController clientController = new ClientController(repo);

       clientController.showRecords();





    }
}