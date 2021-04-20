package com.pl.bg.javamasproject.demo.models;

import com.pl.bg.javamasproject.demo.tools.Looper;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    /*
    @Test
    void fields() {

        List<String> listColumns = Arrays.asList("int","String","long","double");
        List<String> fieldsList = new ArrayList<>();
        Field[] tab =new Patient().getClass().getDeclaredFields();
        Looper.forLoop(0,tab.length, i -> {
        for(int j =0;j<listColumns.size();j++) {

            if (!tab[i].getType().getSimpleName().equals(listColumns.get(j))) {

            } else {
                fieldsList.add(tab[i].getName());
            }
        }

        });
        System.out.println(fieldsList);
    }


     */
}
