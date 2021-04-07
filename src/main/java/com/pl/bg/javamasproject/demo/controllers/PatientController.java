package com.pl.bg.javamasproject.demo.controllers;

import com.pl.bg.javamasproject.demo.SQL.Repository;
import com.pl.bg.javamasproject.demo.models.Patient;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientController  implements ControllerTemplate, Initializable {

    @FXML
    Button button = new Button("ADD NEW PATIENT");
    @FXML
    TextField name = new TextField();
    @FXML
    ComboBox comboBox_client = new ComboBox();
    @FXML
    ComboBox comboBox_card = new ComboBox();

    ObservableList<Integer> list_client = FXCollections.observableArrayList();
    ObservableList<Integer> list_card = FXCollections.observableArrayList();


    @Override
    public void addRecord() {

        new Repository<Patient>().insert(new Patient(name.getText(),(int)comboBox_card.getValue(),(int)comboBox_client.getValue()));

    }

    @Override
    public void deleteRecord() {

        new Repository<Patient>().delete(new Patient(),1);
    }

    @Override
    public void showRecords() {

    }

    @Override
    public void updateRecord() {

    }

    @Override
    public void start(Stage stage) throws IOException {

       Parent root = new FXMLLoader().load(getClass().getClassLoader().getResource("FXML/patientAdding.fxml"));
        Scene scene = new Scene(root,280,250);
        stage.setScene(scene);
        stage.show();


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 0; i< 10;i++) {
            list_card.add(i);
            list_client.add(i);
        }


    comboBox_client.getItems().addAll(list_client);
    comboBox_card.getItems().addAll(list_card);

    }
}
