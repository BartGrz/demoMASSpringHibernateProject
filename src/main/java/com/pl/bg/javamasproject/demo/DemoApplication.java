package com.pl.bg.javamasproject.demo;


import com.pl.bg.javamasproject.demo.Beans.AppConfiguration;
import com.pl.bg.javamasproject.demo.GUI.PatientApplication;
import com.pl.bg.javamasproject.demo.controllers.PatientController;
import com.pl.bg.javamasproject.demo.logic.RunMainWindow;
import com.pl.bg.javamasproject.demo.models.PatientRepository;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.IOException;
import java.net.Proxy;


@SpringBootApplication
public class DemoApplication    {

    //  static Stage stage = new Stage();
    @FXML
    public Button button = new Button();



    public static void main(String[] args) {

        //SpringApplication.run(DemoApplication.class, args);
        Application.launch(PatientApplication.class,args);

    }





}

