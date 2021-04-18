package com.pl.bg.javamasproject.demo.models;
import com.pl.bg.javamasproject.demo.tools.Looper;
import javafx.fxml.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;


abstract class EntityTemplate  {
    public static final Logger logger = LoggerFactory.getLogger(EntityTemplate.class);

    /**
     * @method template impl {
     *
     *      public List<String> fields () {
     *         List<String> fieldsList = new ArrayList<>();
     *         Field[] tab =new (ModelClass).getClass().getDeclaredFields();
     *         Looper.forLoop(0,tab.length,i -> fieldsList.add(tab[i].getName()));
     *         return fieldsList;
     *     }
     * }
     * @return list of declared fields
     */
    public static List<String> fields(Class clazz) {

        List<String> fieldsList = new ArrayList<>();
        Field[] tab =clazz.getClass().getDeclaredFields();

        Looper.forLoop(0,tab.length,i -> {
            for (int j = 0; j < listColumns.size(); j++) {
                if (!tab[i].getType().getSimpleName().equals(listColumns.get(j))) {
                } else {
                    fieldsList.add(tab[i].getName());
                }
            }
        });
        return fieldsList;
    }
    public abstract List<String> allFields();
    public abstract EnumSet fieldsEnum();
    public static List<String> listColumns = Arrays.asList("int","String","long","double");



}
