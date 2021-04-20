package com.pl.bg.javamasproject.demo.MP1;

import com.pl.bg.javamasproject.demo.models.Patient;
import com.sun.javafx.collections.ListListenerHelper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestTest {

    @Test
    void tryThat() {
        List list =  new com.pl.bg.javamasproject.demo.MP1.Test.TestBuilder<Patient,Class>()
                .value(Patient.fieldsNames.PATIENT_NAME)
                .value(Patient.fieldsNames.ID_CARD)
                .build()
                .GenerateSpecificSelectResult(Patient.class);

        System.out.println(list.get(0).toString());
    }
}