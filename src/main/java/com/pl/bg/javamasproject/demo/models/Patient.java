package com.pl.bg.javamasproject.demo.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {

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
}
