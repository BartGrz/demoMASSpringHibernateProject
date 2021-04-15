package com.pl.bg.javamasproject.demo.models;

import com.pl.bg.javamasproject.demo.tools.Looper;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.lang.reflect.Field;

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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
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

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

        ID,PATIENT_NAME,ID_CARD,ID_CLIENT,CLIENT

    }

}


