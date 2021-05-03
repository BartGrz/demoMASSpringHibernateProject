package com.pl.bg.javamasproject.demo;


import com.pl.bg.javamasproject.demo.controllers.PatientController;
import com.pl.bg.javamasproject.demo.models.Patient;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.spring.SpringFxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringbootJavaFxApplication extends Application {


    private ConfigurableApplicationContext context ;



    public void init()   {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.context = new SpringApplicationBuilder()
                .sources(DemoApplication.class)
                .run(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FxWeaver fxWeaver = this.context.getBean(FxWeaver.class);
     Parent root = fxWeaver.loadView(PatientController.class);
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    @Override
    public void stop()   {
       this.context.close();
        Platform.exit();
    }

}
