package com.pl.bg.javamasproject.demo.controllers;

import com.pl.bg.javamasproject.demo.DemoApplication;
import com.pl.bg.javamasproject.demo.GUI.PatientApplication;
import com.pl.bg.javamasproject.demo.SQL.TestBul;
import com.pl.bg.javamasproject.demo.SQL.SelectQueryBuilder;
import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.models.Patient;
import com.pl.bg.javamasproject.demo.models.PatientRepository;
import com.pl.bg.javamasproject.demo.tools.FXML_tools.TableViewBuild;
import com.pl.bg.javamasproject.demo.tools.FXML_tools.TableViewCreator;
import com.pl.bg.javamasproject.demo.tools.Looper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import com.pl.bg.javamasproject.demo.GUI.PatientApplication.StageReadyEvent;


@Component
public class PatientController  implements Initializable {

    public static final Logger logger = LoggerFactory.getLogger(PatientController.class);


    private  PatientRepository patientRepository;


    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }





    @FXML
    Button button = new Button("ADD NEW PATIENT");
    @FXML
    TextField name = new TextField();
    @FXML
    ComboBox comboBox_client = new ComboBox();
    @FXML
    ComboBox comboBox_card = new ComboBox();
    @FXML
    ComboBox comboBox_chooseClient = new ComboBox();
    @FXML
    TableView tableView = new TableView(), tableView_client = new TableView();

    private List<Client> listOfClients = new ArrayList<>();
    private List<Client> list_clients = new ArrayList<>();
    private List<Patient> listOfPatients = new ArrayList<>();
    private final ObservableList<String> list_client = FXCollections.observableArrayList();
    private final ObservableList<Integer> list_card = FXCollections.observableArrayList();
    private final Map<String, Integer> map = new HashMap<>();

    public void addRecord() {
        /*
        new InsertQueryBuilder.Builder<Patient>()
                .insertInto(Patient.class)
                .fields(Patient.getListOfTableFields())
                .value(name.getText())
                .value(comboBox_card.getValue())
                .value(map.get(comboBox_client.getValue().toString()))
                .end()
                .generateAndExecuteSQL();


         */

        patientRepository.save(
                Patient.builder()
                        .patient_name(name.getText())
                        .id_client(map.get(comboBox_client.getValue().toString()))
                        .id_card((int) comboBox_card.getValue())
                        .build()
        );

        refreshTables();
    }



     @Override
     public void initialize(URL location, ResourceBundle resources) {

        begin();
     }

    public void begin() {


        for (int i = 0; i < 25; i++) {
            list_card.add(i);
        }
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

        tableView.getColumns().addAll(t_1, t_3);
        tableView_client.getColumns().addAll(t_4, t_5, t_6);


        fillingInComboBox();
        /*
        refreshTables();


         */
    }



    public void fillingInComboBox() {

        List<Client> listClientNames = new SelectQueryBuilder.Builder<Client, Patient>()
                .build()
                .GenerateBasicSelectResult(Client.class);


        Looper.forLoop(0, listClientNames.size(),
                i -> map.put(listClientNames.get(i).getClient_name(), listClientNames.get(i).getId()));

        Looper.forLoop(0, listClientNames.size(), i -> {
            comboBox_client.getItems().add(listClientNames.get(i).getClient_name());
            comboBox_chooseClient.getItems().add(listClientNames.get(i).getClient_name());
        });


    }

    public void refreshTables() {

        listOfPatients = new ArrayList<>();
        list_clients = new ArrayList<>();
        listOfClients = new ArrayList<>();

        comboBox_chooseClient.setValue(null);
        tableView.getItems().removeAll(tableView.getItems());
        tableView_client.getItems().removeAll(tableView_client.getItems());

        list_clients = new SelectQueryBuilder.Builder<Client, Patient>()
                .build()
                .GenerateBasicSelectResult(Client.class);


        List<Patient> list_patients = patientRepository.findAll();

        if (!list_clients.isEmpty()) {

            for (int i = 0; i < list_clients.size(); i++) {

                listOfClients.add(list_clients.get(i));
            }
        } else {

        }
        Looper.forLoop(0, list_patients.size(), i -> listOfPatients.add(list_patients.get(i)));
        Looper.forLoop(0, listOfPatients.size(), i -> tableView.getItems().add(listOfPatients.get(i)));
        Looper.forLoop(0, listOfClients.size(), i -> tableView_client.getItems().add(listOfClients.get(i)));

    }

    public void changeClient() {

        listOfPatients = new ArrayList<>();
        list_clients = new ArrayList<>();
        listOfClients = new ArrayList<>();

        TableViewBuild.removeAllFromView(tableView);
        TableViewBuild.removeAllFromView(tableView_client);

        int id = map.get(comboBox_chooseClient.getValue().toString());


        list_clients = TestBul.<Client, Patient>builder()
                .set(Client.fieldsNames.PATIENTS)
                .where(Patient.fieldsNames.ID_CLIENT)
                .equal(id)
                .build()
                .generateJoinSelectResult(Client.class);

        for (int i = 0; i < list_clients.size(); i++) {

            listOfClients.add(list_clients.get(i));
        }

        if (!list_clients.isEmpty()) {

            listOfPatients = new SelectQueryBuilder.Builder<>().build().getFromSet(list_clients.get(0).getPatients());

            TableViewBuild.addFromList(tableView, listOfPatients);
            tableView_client.getItems().add(listOfClients.get(0));
        } else {

        }

    }
}