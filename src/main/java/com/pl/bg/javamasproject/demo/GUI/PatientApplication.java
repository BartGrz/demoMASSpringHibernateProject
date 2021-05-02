package com.pl.bg.javamasproject.demo.GUI;

import com.pl.bg.javamasproject.demo.DemoApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class PatientApplication extends Application {

private ConfigurableApplicationContext context;



    @Override
    public void init()  {
        context = new SpringApplicationBuilder(DemoApplication.class).run();
    }

    public void start(Stage stage) throws IOException {

        Parent root =  FXMLLoader.load(getClass().getClassLoader().getResource("FXML/patientAdding.fxml"));
        stage.setScene(new Scene(root, 574, 382));

        context.publishEvent(new StageReadyEvent(stage));
    }


    @Override
    public void stop()   {
        context.close();
        Platform.exit();
    }

    public static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }

         public Stage getStage() {

            return (Stage) getSource();
         }
     }
}
