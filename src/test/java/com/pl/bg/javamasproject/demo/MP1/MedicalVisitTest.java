package com.pl.bg.javamasproject.demo.MP1;


import org.junit.jupiter.api.Test;


class MedicalVisitTest {


    @Test
    void checkIfConstructorAddingDoctorToList() {
        Doctor doctor = new Doctor("Jan", "kanwa", "", Employee.Contract.B2B, Doctor.Specialization.ONCOLOGIST);
        Pet pet = new Pet("foo", Pet.Species.MAMMAL.toString(), 2009);
        MedicalVisit medicalVisit = new MedicalVisit(MedicalVisit.VisitType.CONTROL,pet , doctor);
        System.out.println(medicalVisit.getDoctors().stream().findAny().get().getPatients());

    }

    @Test
    void findIfFindingDoctorAssosicated_ok() {

       // System.out.println(medicalVisit.findDoctorAssosiciatedWithVisit(1));
    }
}