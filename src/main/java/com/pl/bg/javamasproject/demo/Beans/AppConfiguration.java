package com.pl.bg.javamasproject.demo.Beans;
import com.pl.bg.javamasproject.demo.controllers.PatientController;
import com.pl.bg.javamasproject.demo.models.Client;

import com.pl.bg.javamasproject.demo.models.Patient;
import com.pl.bg.javamasproject.demo.models.PatientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
   public PatientRepository patientRepository() {

        PatientRepository patientRepository = new PatientService();
        return patientRepository;
    }

    @Bean(name = "patientBean")
    public Patient patient(){
        return new Patient();
    }
    @Bean(name = "clientBean")
   public Client client() {
        return new Client();
    }


}
