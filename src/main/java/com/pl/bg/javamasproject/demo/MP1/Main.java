package com.pl.bg.javamasproject.demo.MP1;

import com.pl.bg.javamasproject.demo.tools.Looper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Optional;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws IOException {


        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
       Reception reception = context.getBean(Reception.class);



        // Client client = new Client("jan","janusz","602555444" ); //Optional.of("janjanusz@gmail")
        //Client client_2 = new Client("Andrzej","Nowak","192554887");
        Client client_3 = new Client("Tomasz","Zawada","669112364");

       // reception.addClientToSystem(client,new Pet(Species.MAMMAL,"kropka"));
      // reception.addClientToSystem(client_2,new Pet(Species.REPTILE,"smok"));
        logger.info(""+Reception.showClientsFromSystem());
        logger.info(""+Reception.showPetsFromSystem());
        logger.info(""+Reception.findClientById(4));
        logger.info(""+Reception.findClientById(1));
       // reception.addPetToExistingClient(1,new Pet(Species.BIRD,"Coco"));

        logger.info(""+Reception.filterBySpecies(Species.REPTILE));
      //  reception.addClientToSystem(client_3,new Pet(Species.AMPHIBIAN,"omaha"));
    }

}
