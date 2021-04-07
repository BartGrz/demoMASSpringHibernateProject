package com.pl.bg.javamasproject.demo.controllers;

import javafx.stage.Stage;

import java.io.IOException;

public interface ControllerTemplate {

    void addRecord();
    void deleteRecord();
    void showRecords();
    void updateRecord();
    void start(Stage stage) throws IOException;

}
