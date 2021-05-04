package com.pl.bg.javamasproject.demo.controllers;

import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.models.ClientRepository;
import com.pl.bg.javamasproject.demo.models.Patient;
import com.pl.bg.javamasproject.demo.models.PatientRepository;
import com.pl.bg.javamasproject.demo.tools.FXML_tools.TableViewBuild;
import com.pl.bg.javamasproject.demo.tools.FXML_tools.TableViewCreator;
import com.pl.bg.javamasproject.demo.tools.Looper;
import com.sun.xml.bind.v2.TODO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.NoArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;


@Component
@FxmlView("PatientController.fxml")
@NoArgsConstructor
public class PatientController implements Initializable {

    public static final Logger logger = LoggerFactory.getLogger(PatientController.class);


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
    @FXML
    TextField textfield_idToDelete = new TextField();
    @FXML
    ImageView logo_vet = new ImageView();
    private File file_logo = new File(System.getProperty("user.home") + "\\Desktop\\" + "lekarz-weterynarii.jpg");

    private List<Client> listOfClients = new ArrayList<>();
    private List<Client> list_clients = new ArrayList<>();
    private final ObservableList<String> list_client = FXCollections.observableArrayList();
    private final ObservableList<Integer> list_card = FXCollections.observableArrayList();
    private final Map<String, Integer> map = new HashMap<>();

    @FXML
    public void addRecord() {


        int id_client = map.get(comboBox_client.getValue().toString());
        patientRepository.saveWithParameters(name.getText(), id_client, (int) comboBox_card.getValue());

        PopUp popUp = new PopUp();

        popUp.start_ok("PATIENT ADDED");
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
        Image image = new Image(file_logo.toURI().toString());
        logo_vet.setImage(image);

        comboBox_client.getItems().addAll(list_client);
        comboBox_card.getItems().addAll(list_card);


        TableColumn t_1 = TableViewCreator.<String, Patient>builder()
                .columnName("NAME")
                .classField("patient_name")
                .build()
                .buildColumn();
        TableColumn t_2 = TableViewCreator.<Integer, Patient>builder()
                .columnName("ID")
                .classField("id")
                .build()
                .buildColumn();
        TableColumn t_3 = TableViewCreator.<Integer, Patient>builder()
                .columnName("IDCARD")
                .classField("id_card")
                .build()
                .buildColumn();
        TableColumn t_4 = TableViewCreator.<String, Client>builder()
                .columnName("Name")
                .classField("client_name")
                .build()
                .buildColumn();
        TableColumn t_5 = TableViewCreator.<Integer, Client>builder()
                .columnName("ID")
                .classField("id")
                .build()
                .buildColumn();
        TableColumn t_6 = TableViewCreator.<String, Client>builder()
                .classField("client_name")
                .columnName("ClientName")
                .build()
                .buildColumn();

        tableView.getColumns().addAll(t_2, t_1, t_3);
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

        list_clients = new ArrayList<>();
        listOfClients = new ArrayList<>();

        TableViewBuild.removeAllFromView(tableView);
        TableViewBuild.removeAllFromView(tableView_client);

        int id = map.get(comboBox_chooseClient.getValue().toString());

        List<Patient> pat = clientRepository.findAllPatientsByClientId(id);
        Client client = clientRepository.findById(id).get();
        listOfClients.add(client);

        TableViewBuild.addFromList(tableView, pat);
        TableViewBuild.addFromList(tableView_client, listOfClients);

    }

    @FXML
    public void delete() {

        int id = Integer.parseInt(textfield_idToDelete.getText());

        if (patientRepository.existById(id)) {

            patientRepository.deleteById(id);
            textfield_idToDelete.setText("");
            new PopUp().start_ok("PATIENT DELETED");
            refreshTables();

        } else {

            textfield_idToDelete.setText("");
            new PopUp().start_error("PATIENT NOT FOUND");
        }

    }

    public void update() {


    }
}