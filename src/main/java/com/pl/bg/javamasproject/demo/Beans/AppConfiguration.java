package com.pl.bg.javamasproject.demo.Beans;


import com.pl.bg.javamasproject.demo.controllers.ClientController;
import com.pl.bg.javamasproject.demo.controllers.PatientController;
import com.pl.bg.javamasproject.demo.logic.RunMainWindow;
import com.pl.bg.javamasproject.demo.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@Configuration
@ComponentScan(basePackages = {"com.pl.bg.javamasproject.demo.models"})
@Import(ClientController.class)
public class AppConfiguration {


}
