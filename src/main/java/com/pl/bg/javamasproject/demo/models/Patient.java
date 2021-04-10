package com.pl.bg.javamasproject.demo.models;

import com.pl.bg.javamasproject.demo.tools.Looper;

import javax.persistence.*;
import java.lang.annotation.ElementType;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;

@Entity
@Table(name = "patients")
public class Patient extends EntityTemplate{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "id_card")
    private int id_card;
    @Column(name = "id_client")
    private int id_client;

    public Patient(String name, int idCard, int idClient) {
        this.name = name;
        this.id_card = idCard;
        this.id_client = idClient;
    }

    public Patient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "('"+ name +"'" +","+id_card +","+ id_client+")";
    }

  //  Set<P> s = Collections.synchronizedSet(EnumSet.noneOf(Fields.class));

   @Override
    public  List<String> fields () {

        List<String> fieldsList = new ArrayList<>();
        Field[] tab =new Patient().getClass().getDeclaredFields();


        Looper.forLoop(0,tab.length,i -> fieldsList.add(tab[i].getName()));

        return fieldsList;
    }

    @Override
    public EnumSet fieldsEnum() {

        return EnumSet.allOf(fieldsNames.class);
    }

    public enum fieldsNames{

        ID,NAME,ID_CARD,ID_CLIENT

    }

}


