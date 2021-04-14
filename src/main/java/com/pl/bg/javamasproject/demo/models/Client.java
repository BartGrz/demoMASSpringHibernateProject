package com.pl.bg.javamasproject.demo.models;


import com.pl.bg.javamasproject.demo.tools.Looper;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.annotation.PostConstruct;
import javax.naming.Name;
import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.*;

@Entity
@Table(name = "clients")
public class Client extends EntityTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "client_name")
    private String client_name;
    @Column(name = "client_number")
    private int client_number;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private Set<Patient> patients ;

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public int getClient_number() {
        return client_number;
    }

    public void setClient_number(int client_number) {
        this.client_number = client_number;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "('" + client_name + "') + " + getPatients() ;
    }


    /**
     * @return list of declared fields
     * @method template impl {
     * public List<String> fields () {
     * List<String> fieldsList = new ArrayList<>();
     * Field[] tab =new (ModelClass).getClass().getDeclaredFields();
     * Looper.forLoop(0,tab.length,i -> fieldsList.add(tab[i].getName()));
     * return fieldsList;
     * }
     * }
     */
    @Override
    public List<String> fields() {


        List<String> fieldsList = new ArrayList<>();
        Field[] tab =new Client().getClass().getDeclaredFields();
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

        return EnumSet.allOf(Patient.fieldsNames.class);
    }

    public enum fieldsNames {

       ID,CLIENT_NAME,PATIENTS,CLIENT_NUMBER
    }


}
