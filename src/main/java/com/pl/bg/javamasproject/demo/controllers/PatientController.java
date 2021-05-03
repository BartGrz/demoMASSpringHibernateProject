package com.pl.bg.javamasproject.demo.controllers;


import com.pl.bg.javamasproject.demo.SQL.TestBul;
import com.pl.bg.javamasproject.demo.SQL.SelectQueryBuilder;
import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.models.ClientRepository;
import com.pl.bg.javamasproject.demo.models.Patient;
import com.pl.bg.javamasproject.demo.models.PatientRepository;
import com.pl.bg.javamasproject.demo.tools.FXML_tools.TableViewBuild;
import com.pl.bg.javamasproject.demo.tools.FXML_tools.TableViewCreator;
import com.pl.bg.javamasproject.demo.tools.Looper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.*;


@Component
@FxmlView("PatientController.fxml")
public class PatientController implements Initializable {

    public static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    public PatientController() {
    }

    private PatientRepository patientRepository;
    private ClientRepository clientRepository;

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
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

    @FXML
    public void addRecord() {


        patientRepository.save(
                Patient.builder()
                        .patient_name("aa")
                        .id_client(2)
                        .id_card(7)
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
        refreshTables();

    }


    public void fillingInComboBox() {


        List<Client> listClientNames = clientRepository.findAll();


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

        list_clients = clientRepository.findAll();
        List<Patient> list_patients = patientRepository.findAll();

        TableViewBuild.addFromList(tableView, list_patients);
        TableViewBuild.addFromList(tableView_client, list_clients);

    }

    public void changeClient() {

        listOfPatients = new ArrayList<>();
        list_clients = new ArrayList<>();
        listOfClients = new ArrayList<>();


        TableViewBuild.removeAllFromView(tableView);
        TableViewBuild.removeAllFromView(tableView_client);

        int id = map.get(comboBox_chooseClient.getValue().toString());

        List<Patient> pat = clientRepository.findAllPatientsByClientId(id);
        Client client = clientRepository.findById(id).get();
        listOfClients.add(client);

        for (int i = 0; i < pat.size(); i++) {
            tableView.getItems().add(pat.get(i));
        }
        for (int i = 0; i < listOfClients.size(); i++) {
            tableView_client.getItems().add(listOfClients.get(i));
        }

    }
}