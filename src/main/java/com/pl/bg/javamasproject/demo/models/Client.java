package com.pl.bg.javamasproject.demo.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client  {

    @Id
    private int id;
    private String name;
    @Column(name = "id_patient")
    private int id_patient;



    public Client( String name, int idPatient) {

        this.name = name;
        this.id_patient = idPatient;
    }

    public Client() {

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

    public int getIdPatient() {
        return id_patient;
    }

    public void setIdPatient(int idPatient) {
        this.id_patient = idPatient;
    }

    @Override
    public String toString() {
        return "('" + name + "'" +
                "," + id_patient +")" ;
    }


}
