package com.pl.bg.javamasproject.demo;

import com.pl.bg.javamasproject.demo.Beans.AppConfiguration;
import com.pl.bg.javamasproject.demo.controllers.PatientController;
import com.pl.bg.javamasproject.demo.models.PatientRepository;
import javafx.application.Application;

import javafx.stage.Stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


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
