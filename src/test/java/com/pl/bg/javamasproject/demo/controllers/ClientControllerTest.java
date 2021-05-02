package com.pl.bg.javamasproject.demo.controllers;


import com.pl.bg.javamasproject.demo.SQL.InsertQueryBuilder;
import com.pl.bg.javamasproject.demo.SQL.SelectQueryBuilder;
import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.models.Patient;
import org.dom4j.tree.ContentListFacade;
import org.junit.jupiter.api.Test;

import java.util.List;


class ClientControllerTest {

    @Test
    void showRecord() {

       List<Client> list = new SelectQueryBuilder.Builder<Client,Patient>()
               .joinSet(Client.fieldsNames.PATIENTS)
               .where(Patient.fieldsNames.ID_CLIENT)
               .equal(3)
               .build()
               .GenerateJoinSelectResult(Client.class);

        System.out.println(list);

        List<Patient> lista = new SelectQueryBuilder.Builder<Patient,Client>()
                .where(Patient.fieldsNames.ID_CLIENT).equal(3)
                .build()
                .GenerateBasicSelectResult(Patient.class);



        System.out.println(lista);
    }

    @Test
    void addRecord() {

        System.out.println(new Client().allFields());
        List list = new Client().allFields();
        list.remove(3);
        list.remove(0);


        new InsertQueryBuilder.Builder<Client>()
                .insertInto(Client.class)
                .fields(list)
                .value("Tomasz")
                .value(2)
                .end().generateAndExecuteSQL();


    }


}