package com.pl.bg.javamasproject.demo.MP1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicalVisitTest {
    Doctor doctor =new Doctor("Jan","kanwa","",Employee.Contract.B2B,Doctor.Specialization.ONCOLOGIST);
    MedicalVisit medicalVisit = new MedicalVisit(MedicalVisit.VisitType.CONTROL,new Pet("foo",Pet.Species.MAMMAL.toString(),2009),doctor);

    @Test
    void checkIfConstructorAddingDoctorToList(){
        System.out.println(medicalVisit );
    }
}