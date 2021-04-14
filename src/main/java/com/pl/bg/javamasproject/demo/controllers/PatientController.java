package com.pl.bg.javamasproject.demo.controllers;

import com.pl.bg.javamasproject.demo.SQL.InsertQueryBuilder;
import com.pl.bg.javamasproject.demo.SQL.Repository;
import com.pl.bg.javamasproject.demo.SQL.SelectQueryBuilder;
import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.models.Patient;
import com.pl.bg.javamasproject.demo.tools.FXML_tools.TableViewCreator;

import com.pl.bg.javamasproject.demo.tools.Looper;
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
    TableView tableView = new TableView() ,tableView_client = new TableView();

    ObservableList<String> list_client = FXCollections.observableArrayList();
    ObservableList<Integer> list_card = FXCollections.observableArrayList();

    @Override
    public void addRecord() {

        List<Client> getNameById = new SelectQueryBuilder.Builder<Client,Patient>()
                .where(Client.fieldsNames.CLIENT_NAME)
                .equal(comboBox_client.getValue())
                .build()
                .GenerateBasicSelectResult(Client.class);

        int fetchIDFromTableByName = getNameById.stream().map(Client::getClient_number).collect(Collectors.toList()).get(0);
        System.out.println(fetchIDFromTableByName);
    }

    @Override
    public void deleteRecord() {


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
        Scene scene = new Scene(root, 520, 250);
        stage.setScene(scene);
        stage.show();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboBox_client.getItems().addAll(list_client);
        comboBox_card.getItems().addAll(list_card);


        TableColumn t_1 = new TableViewCreator.Builder<String, Patient>()
                .setColumnName("NAME")
                .setField(Patient.fieldsNames.PATIENT_NAME)
                .build()
                .buildColumn();
        TableColumn t_3 = new TableViewCreator.Builder<Integer, Patient>()
                .setColumnName("IDCARD")
                .setField(Patient.fieldsNames.ID_CARD)
                .build()
                .buildColumn();
        TableColumn t_4 = new TableViewCreator.Builder<String, Client>()
                .setColumnName("Name")
                .setField(Client.fieldsNames.CLIENT_NAME)
                .build()
                .buildColumn();
        TableColumn t_5 = new TableViewCreator.Builder<Integer, Client>()
                .setColumnName("ID")
                .setField(Client.fieldsNames.ID)
                .build()
                .buildColumn();
        TableColumn t_6 = new TableViewCreator.Builder<Integer, Client>()
                .setColumnName("CLientNumber")
                .setField(Client.fieldsNames.CLIENT_NUMBER)
                .build()
                .buildColumn();

        tableView.getColumns().addAll(t_1,t_3);
        tableView_client.getColumns().addAll(t_4,t_5,t_6);

        List <Client> list_clients = new SelectQueryBuilder.Builder<Client,Patient>()
                                    .joinSet(Client.fieldsNames.PATIENTS)
                                    .where(Client.fieldsNames.ID)
                                    .equal(3)
                                    .build()
                                    .GenerateJoinSelectResult(Client.class);

        List<Client> listClientNames = new SelectQueryBuilder.Builder<Client,Patient>()
                .build()
                .GenerateBasicSelectResult(Client.class);




        List<Patient> listOfPatients = new SelectQueryBuilder.Builder<>().build().getFromSet(list_clients.get(0).getPatients());
        List<Client> listOfClients = new ArrayList<>();

        for (int i = 0;i<list_clients.size();i++) {

            listOfClients.add(list_clients.get(i));

        }
        Looper.forLoop(0,listClientNames.size(),i -> comboBox_client.getItems().add(listClientNames.get(i).getClient_name()));
        Looper.forLoop(0,listOfPatients.size(),i -> tableView.getItems().add(listOfPatients.get(i)));
        Looper.forLoop(0, listOfClients.size(),i -> tableView_client.getItems().add(listOfClients.get(i)));


    }


}