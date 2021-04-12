package com.pl.bg.javamasproject.demo.models;
import com.pl.bg.javamasproject.demo.tools.Looper;
import javafx.fxml.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
import javax.persistence.criteria.CriteriaBuilder;
import java.net.URL;
import java.util.*;


abstract class EntityTemplate  {
    public static final Logger logger = LoggerFactory.getLogger(Patient.class);

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
    public abstract List<String> fields();
    public abstract EnumSet fieldsEnum();
    public List<String> listColumns = Arrays.asList("int","String","long","double");


    @PostConstruct
    public List<String> validateEnumVerTableColumns() {

        List<String> listFields = fields();
        EnumSet fieldsEnum = fieldsEnum();

        List<String> listOfMissingColumnsInEnumClass = new ArrayList<>();
        int index = 0;
        if (listFields.size() == fieldsEnum.size()) {

        } else {

            index = listFields.size() - fieldsEnum.size();

            Looper.forLoop(listFields.size()-index, listFields.size()
                    , i -> listOfMissingColumnsInEnumClass.add(listFields.get(i)));

            return listOfMissingColumnsInEnumClass;
        }

        return null;
    }
    public boolean checkIfEnumEqualsDeclaredFields(List<String> list) {

        if (list==null) {
            return true;
        }
        else return false;

    }

    //FIXME  nie dziala, trzeba sprawic by sie validowalo bez wywolywania bezposredniego
    @PostPersist
    public void validate(){

        List<String> list = validateEnumVerTableColumns();

        if(!checkIfEnumEqualsDeclaredFields(validateEnumVerTableColumns())) {

            logger.warn("ERROR , FIELDS MISSING :" + list);

        }else {
            logger.warn("DATA VALIDATION OK ");
        }

    }



}
