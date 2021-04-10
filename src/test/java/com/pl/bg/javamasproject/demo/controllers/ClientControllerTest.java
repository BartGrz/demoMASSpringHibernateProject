package com.pl.bg.javamasproject.demo.controllers;

import com.pl.bg.javamasproject.demo.SQL.Repository;
import com.pl.bg.javamasproject.demo.models.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientControllerTest {

    @Test
    void addRecord() {

        new Repository<Client>().insert(new Client("Jan"));

    }
}