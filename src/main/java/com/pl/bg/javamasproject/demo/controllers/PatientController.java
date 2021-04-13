package com.pl.bg.javamasproject.demo.controllers;

import com.pl.bg.javamasproject.demo.SQL.InsertQueryBuilder;
import com.pl.bg.javamasproject.demo.SQL.Repository;
import com.pl.bg.javamasproject.demo.SQL.SelectQueryBuilder;
import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.models.Patient;
import com.pl.bg.javamasproject.demo.tools.FXML_tools.TableViewCreator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PatientController  implements ControllerTemplate, Initializable {

    @FXML
    Button button = new Button("ADD NEW PATIENT");
    @FXML
    TextField name = new TextField();
    @FXML
    ComboBox comboBox_client = new ComboBox();
    @FXML
    ComboBox comboBox_card = new ComboBox();
    @FXML
    TableView tableView = new TableView();

    ObservableList<Integer> list_client = FXCollections.observableArrayList();
    ObservableList<Integer> list_card = FXCollections.observableArrayList();


    @Override
    public void addRecord() {

        new InsertQueryBuilder.Builder<Patient>()
                .insertInto(Patient.class)
                .fields(new Patient().fields())
                .value(name.getText())
                .value(comboBox_client.getValue())
                .value(comboBox_card.getValue())
                .end()
                .generateAndExecuteSQL();

    }

    @Override
    public void deleteRecord() {

        new Repository<Patient>().delete(new Patient(), 1);
    }

    @Override
    public void showRecords() {

    }

    @Override
    public void updateRecord() {

        new Repository<Patient>().update(new Patient(), 1, "dupa", "name");

    }

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = new FXMLLoader().load(getClass().getClassLoader().getResource("FXML/patientAdding.fxml"));
        Scene scene = new Scene(root, 280, 250);
        stage.setScene(scene);
        stage.show();


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 0; i < 10; i++) {
            list_card.add(i);
            list_client.add(i);
        }


        comboBox_client.getItems().addAll(list_client);
        comboBox_card.getItems().addAll(list_card);


        TableColumn t_1 = new TableViewCreator.Builder<String, Patient>()
                .setColumnName("NAME")
                .setField(Patient.fieldsNames.PATIENT_NAME)
                .build()
                .buildColumn();
        TableColumn t_3 = new TableViewCreator.Builder<String, Patient>()
                .setColumnName("IDCARD")
                .setField(Patient.fieldsNames.ID_CARD)
                .build()
                .buildColumn();
        TableColumn t_4 = new TableViewCreator.Builder<String, String>()
                .setColumnName("ClientName")
                .setField(Client.fieldsName.CLIENT_NAME)
                .build()
                .buildColumn();

        List<Client> lista= new SelectQueryBuilder.Builder<Client,Patient>()
                .joinSet(Client.fieldsName.PATIENTS)
                .setForeignKey(Patient.fieldsNames.ID_CLIENT)
                .setIdVaule(3)
                .build()
                .GenerateJoinSelectResult(Client.class);


        tableView.getColumns().addAll(t_1,t_3);


        Client client = new Client();
        List<Client> cl = new ArrayList<>();


        String patName = lista.stream().map(Client::getPatients).collect(Collectors.toList()).get(0).stream().map(Patient::getPatient_name).collect(Collectors.toList()).get(0);
        int patCard =lista.stream().map(Client::getPatients).collect(Collectors.toList()).get(0).stream().map(Patient::getId_card).collect(Collectors.toList()).get(0);

        Patient patient = new Patient();
        List<Patient> listPat = new ArrayList<>();
        patient.setPatient_name(patName);
        patient.setId_card(patCard);
        listPat.add(patient);



       tableView.getItems().add(listPat.get(0));








    }
}