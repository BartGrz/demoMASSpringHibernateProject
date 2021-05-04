package com.pl.bg.javamasproject.demo.tools.FXML_tools;

import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.models.Patient;
import com.pl.bg.javamasproject.demo.tools.Looper;
import javafx.scene.control.TableColumn;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableViewCreatorTest {

    @Test
    void validate() {
        var classField = "patient_name";
        List<String> fieldsList = new ArrayList<>();
        Field[] tab = Patient.class.getDeclaredFields();

        Looper.forLoop(0,tab.length, i ->fieldsList.add(tab[i].getName()));


        System.out.println( fieldsList.stream().anyMatch(s-> s.equals(classField)));


    }

    @Test
    void createTableColumn() {
        TableColumn t = TableViewCreator.<String,Client>builder()
                .columnName("clientName")
                .classField("client_name")
                .build().buildColumn();


    }
}