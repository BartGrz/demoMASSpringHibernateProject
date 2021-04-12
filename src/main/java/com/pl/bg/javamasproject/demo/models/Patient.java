package com.pl.bg.javamasproject.demo.models;

import com.pl.bg.javamasproject.demo.tools.Looper;
import javafx.fxml.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;


@Entity
@Table(name = "patients")
public class Patient extends EntityTemplate  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "patient_name")
    private String patient_name;
    @Column(name = "id_card")
    private int id_card;
    @Column(name = "id_client")
    private int id_client;
    @ManyToOne
    @JoinColumn(name = "id_client", insertable = false ,updatable = false)
    private Client client;

    public Patient() {
    }


    public int getId_card() {
        return id_card;
    }

    public void setId_card(int id_card) {
        this.id_card = id_card;
    }

    public int getId_client() {
        return id_client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return patient_name;
    }

    public void setName(String name) {
        this.patient_name = name;
    }

    public int getIdCard() {
        return id_card;
    }

    public void setIdCard(int idCard) {
        this.id_card = idCard;
    }

    public int getIdClient() {
        return id_client;
    }

    public void setIdClient(int idClient) {
        this.id_client = idClient;
    }

    @Override
    public String toString() {
        return "('"+ patient_name +"'" +","+id_card +","+ id_client+")";
    }

   @Override
    public  List<String> fields () {

       List<String> fieldsList = new ArrayList<>();
       Field[] tab =new Patient().getClass().getDeclaredFields();
       Looper.forLoop(1,tab.length, i -> {

           for(int j =0;j<listColumns.size();j++) {

               if (!tab[i].getType().getSimpleName().equals(listColumns.get(j))) {
               } else {
                   fieldsList.add(tab[i].getName());
               }
           }

       });
        return fieldsList;
    }

    @Override
    public EnumSet fieldsEnum() {

        return EnumSet.allOf(fieldsNames.class);
    }
    public enum fieldsNames{

        ID,PATIENT_NAME,ID_CARD,ID_CLIENT

    }

}


