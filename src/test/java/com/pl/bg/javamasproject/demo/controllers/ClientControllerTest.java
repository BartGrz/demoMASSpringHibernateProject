package com.pl.bg.javamasproject.demo.controllers;


import com.pl.bg.javamasproject.demo.SQL.InsertQueryBuilder;
import com.pl.bg.javamasproject.demo.SQL.Repository;
import com.pl.bg.javamasproject.demo.SQL.SelectQueryBuilder;
import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.models.Patient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientControllerTest {

    @Test
    void showRecord() {

       List<Client> list = new SelectQueryBuilder.Builder<Client,Patient>()
               .joinSet(Client.fieldsName.PATIENTS)
               .setForeignKey(Patient.fieldsNames.ID_CLIENT)
               .setIdVaule(3)
               .build()
               .GenerateJoinSelectResult(Client.class);

        System.out.println(list);
    }

    @Test
    void addRecord() {
        new InsertQueryBuilder.Builder<Client>()
                .insertInto(Client.class)
                .fields(new Client().fields())
                .value("Tomasz")
                .end().generateAndExecuteSQL();
    }
}