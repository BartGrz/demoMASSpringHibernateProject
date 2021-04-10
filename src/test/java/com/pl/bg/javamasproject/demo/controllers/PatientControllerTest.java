package com.pl.bg.javamasproject.demo.controllers;

import com.pl.bg.javamasproject.demo.SQL.CompleksSelectQuery;
import com.pl.bg.javamasproject.demo.SQL.Repository;
import com.pl.bg.javamasproject.demo.models.Patient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PatientControllerTest {

    @Test
    void updateRecord() {

        new Repository<Patient>().update(new Patient(),1,"testUpdate","name");
    }

    @Test
    void deleteRecord() {

        new Repository<Patient>().delete(new Patient(),4);

    }

    @Test
    void showRecords() {


       List<Patient> patientList =  new CompleksSelectQuery.Builder<Patient>()
               .setColumn(Patient.fieldsNames.NAME)
               .from()
               .where(Patient.fieldsNames.ID)
               .equal(1)
               .end()
               .buildSelectQueryFor(new Patient());

        System.out.println(patientList);
    }

    @Test
    void addRecord() {

        new Repository<Patient>().insert(
                new Patient("kropka",1,1));
    }
}