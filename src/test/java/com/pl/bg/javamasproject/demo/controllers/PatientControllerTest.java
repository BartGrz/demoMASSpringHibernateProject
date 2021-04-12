package com.pl.bg.javamasproject.demo.controllers;


import com.pl.bg.javamasproject.demo.SQL.CompleksSelectQuery;
import com.pl.bg.javamasproject.demo.SQL.InsertQueryBuilder;
import com.pl.bg.javamasproject.demo.SQL.Repository;
import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.models.Patient;

import com.pl.bg.javamasproject.demo.tools.Looper;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Locale;

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

        List<Object[]> patientList = new CompleksSelectQuery.Builder<Patient>()
                .from(new Patient())
                .end()
                .buildSelectQuery();

        Looper.forLoop(0,patientList.size(),i -> {
            for(int j = 0;j<patientList.get(0).length;j++) {

                System.out.println(patientList.get(i)[j]+" ");
            }

        });


    }

    @Test
    void addRecord() {

        new InsertQueryBuilder.Builder<Client>()
                .insertInto(Client.class)
                .fields(new Client().fields())
                .value("Jan")
                .end()
                .generateAndExecuteSQL();

        new InsertQueryBuilder.Builder<Patient>()
                .insertInto(Patient.class)
                .fields(new Patient().fields())
                .value("testest").value(1).value(1)
                .end().generateAndExecuteSQL();
    }
}