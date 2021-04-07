package com.pl.bg.javamasproject.demo;

import com.pl.bg.javamasproject.demo.SQL.Repo;
import com.pl.bg.javamasproject.demo.SQL.Repository;
import com.pl.bg.javamasproject.demo.SQL.SqlCommends;
import com.pl.bg.javamasproject.demo.controllers.PatientController;
import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.models.Patient;
import com.pl.bg.javamasproject.demo.tools.Looper;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class DemoApplication extends Application  {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class, args);

        Application.launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane pane = new Pane();
        Button button = new Button("OK");

        pane.getChildren().add(button);


        primaryStage.setScene(new Scene(pane,100,100));
        primaryStage.show();


        button.setOnAction(event -> {
            try {
                new PatientController().start(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}
