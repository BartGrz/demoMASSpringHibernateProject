package com.pl.bg.javamasproject.demo;

import com.pl.bg.javamasproject.demo.controllers.PatientController;
import com.pl.bg.javamasproject.demo.models.Patient;
import com.pl.bg.javamasproject.demo.tools.FXML_tools.TableViewCreator;
import javafx.application.Application;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@SpringBootApplication
public class DemoApplication extends Application  {



    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class, args);

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        new PatientController().start(new Stage());
    }



}
