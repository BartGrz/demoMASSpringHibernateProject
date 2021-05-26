package com.pl.bg.javamasproject.demo.MP1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.print.Doc;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ToString //(exclude = {"doctors"})
public class MedicalVisit {


    @Getter
    private Enum<VisitType> visitType ;
    @Getter
    private Set<Doctor> doctors = new HashSet<>();
    @Getter
    private final int id = generateId();
    @Getter
    private Pet patient;
    private static int count = 1;
    @Getter
    @Setter
    private VisitInformation visitInformation;

    public int generateId() {
        return count++;
    }

    public MedicalVisit(Enum<VisitType> visitType, Pet patient) {
        this.visitType =visitType;
        this.patient=patient;
        var visitInformation = new VisitInformation(this,null);
        this.visitInformation = visitInformation;
    }
    public MedicalVisit(Enum<VisitType> visitType, Pet patient,Doctor doctor) {
        this.visitType =visitType;
        this.patient=patient;
        doctors.add(doctor);
        doctor.getVisits().add(this);
        doctor.addPatient(patient);
        var visitInformation = new VisitInformation(this,doctor);
        this.visitInformation = visitInformation;
    }
    public enum VisitType {
        CONTROL,CITO,OPERATION
    }

}
