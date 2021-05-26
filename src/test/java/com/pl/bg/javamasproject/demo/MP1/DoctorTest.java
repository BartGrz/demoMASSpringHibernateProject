package com.pl.bg.javamasproject.demo.MP1;

import org.junit.jupiter.api.Test;

public class DoctorTest {
    @Test
    void checkIfDoctorClassMethodsWork(){

        var doctor = new Doctor("Jan", "kanwa", "", Employee.Contract.B2B, Doctor.Specialization.ONCOLOGIST);

        MedicalVisit medicalVisit = new MedicalVisit(MedicalVisit.VisitType.OPERATION, new Pet("coco", Pet.convertFromEnum(Pet.Species.REPTILE)));
        doctor.addVisit(medicalVisit);
        doctor.addVisit(medicalVisit);
        System.out.println(doctor.findVisit(1));
    }

}
