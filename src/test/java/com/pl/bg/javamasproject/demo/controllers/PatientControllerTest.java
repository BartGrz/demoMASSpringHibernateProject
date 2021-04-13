package com.pl.bg.javamasproject.demo.controllers;



import com.pl.bg.javamasproject.demo.SQL.InsertQueryBuilder;
import com.pl.bg.javamasproject.demo.SQL.Repository;

import com.pl.bg.javamasproject.demo.models.Patient;

import com.pl.bg.javamasproject.demo.tools.Looper;
import org.junit.jupiter.api.Test;
import java.util.List;


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
                .fields(new Patient().fields())
                .value("kropka")
                .value(3)
                .value(3)
                .end()
                .generateAndExecuteSQL();
    }
}