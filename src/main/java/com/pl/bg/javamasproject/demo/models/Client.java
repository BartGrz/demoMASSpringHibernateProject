package com.pl.bg.javamasproject.demo.models;


import com.pl.bg.javamasproject.demo.tools.Looper;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client extends EntityTemplate {

    @Id
    private int id;
    private String name;

    public Client( String name ) {

        this.name = name;
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


    @Override
    public String toString() {
        return "('" + name + "')" ;
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
      Looper.forLoop(0,tab.length, i -> fieldsList.add(tab[i].getName()));

        return fieldsList;
    }

    @Override
    public EnumSet fieldsEnum() {

        return null;
    }
    enum fieldsName {

       ID,NAME

    }


}
