package com.pl.bg.javamasproject.demo.controllers;

import com.pl.bg.javamasproject.demo.SQL.CompleksSelectQuery;
import com.pl.bg.javamasproject.demo.SQL.Repository;
import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.models.Patient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientControllerTest {

    @Test
    void showRecord() {

        List<String> list = new CompleksSelectQuery.Builder<Patient>()
                .from(new Patient())
                .joinTable(new Client())
                .setColumn(Client.fieldsName.CLIENT_NAME)
                .setColumn(Patient.fieldsNames.PATIENT_NAME)
                .setColumn(Patient.fieldsNames.ID_CARD)
                .on(Patient.fieldsNames.ID_CLIENT,Client.fieldsName.ID)
                .where(Patient.fieldsNames.ID)
                .equal(1)
                .end()
                .buildSelectQuery();

        System.out.println(list);
    }
}