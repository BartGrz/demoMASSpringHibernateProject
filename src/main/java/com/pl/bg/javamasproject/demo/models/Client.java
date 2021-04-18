package com.pl.bg.javamasproject.demo.models;


import com.pl.bg.javamasproject.demo.tools.Looper;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    @Override
    public  List<String> allFields () {

        List<String> fieldsList = new ArrayList<>();
        Field[] tab =this.getClass().getDeclaredFields();
        Looper.forLoop(0,tab.length, i -> fieldsList.add(tab[i].getName()));
        return fieldsList;
    }

    public static List<String> getListOfTableFields() {

        return fields(Client.class);
    }
    @Override
    public EnumSet fieldsEnum() {

        return EnumSet.allOf(Client.fieldsNames.class);
    }
    public enum fieldsNames {

       ID,CLIENT_NAME,CLIENT_NUMBER,PATIENTS
    }

    @PostConstruct
    public void validate() {

        List<String> list = new ArrayList<>(allFields());
        boolean[] tab = new boolean[list.size()];

        int index = 0;
        for (Iterator it = fieldsEnum().iterator(); it.hasNext(); ) {
            String enumField = it.next().toString().toLowerCase();
            if (list.get(index).equals(enumField)) {
                tab[index] = true;
            } else {
                tab[index] = false;
            }
            index += 1;
        }
        for (int i = 0; i<tab.length;i++) {

            if (tab[i]) {

            }else {
                logger.error("NOT ALL FIELDS FROM CLASS ARE ADDED TO ENUM CLASS, fields missing : " + list.get(i));
                break;
            }
        }
        logger.info("VALIDATION OK");
    }


}
