package com.pl.bg.javamasproject.demo.MP1;

import org.junit.jupiter.api.Test;

public class DoctorTest {

    @Test
    void checkIfDoctorClassMethodsWork(){
        Doctor doctor = new Doctor("Jan", "kanwa", "", Employee.Contract.B2B, Doctor.Specialization.ONCOLOGIST);

        MedicalVisit medicalVisit = new MedicalVisit(MedicalVisit.VisitType.OPERATION, new Pet("coco", Pet.convertFromEnum(Pet.Species.REPTILE)),doctor);

        doctor.addVisit(medicalVisit);
       // doctor.addVisit(medicalVisit);
        System.out.println(medicalVisit.findDoctorAssosiciatedWithVisit(doctor));
    }

    @Test
    void findIfMethod_findVisit_returnsOptional(){
        Doctor doctor = new Doctor("Jan", "kanwa", "", Employee.Contract.B2B, Doctor.Specialization.ONCOLOGIST);

        doctor.addPatientToDoctor(new Pet("coco", Pet.convertFromEnum(Pet.Species.REPTILE)));
        System.out.println(doctor.getPatients().stream().findAny().get().getMedicalCareHIstory());

      //  System.out.println( doctor.findVisit(1));
    }

}
