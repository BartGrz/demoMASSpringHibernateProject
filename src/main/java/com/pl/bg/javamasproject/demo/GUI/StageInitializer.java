package com.pl.bg.javamasproject.demo.GUI;

import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;
import com.pl.bg.javamasproject.demo.GUI.PatientApplication.StageReadyEvent;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    @Override
    public void onApplicationEvent(StageReadyEvent event) {
      Stage stage =  event.getStage();
      stage.show();
    }





}
