package com.pl.bg.javamasproject.demo.MP1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.print.Doc;
import java.util.*;

/**
 * asocjacja (medicalVisit- doctor)
 */
@ToString (exclude = {"asociateVisitWithDoc"})
public class MedicalVisit {


    @Getter
    private Enum<VisitType> visitType;
    @Getter
    private Set<Doctor> doctors = new HashSet<>(); // wiecej sensu ma 1 doktor przypisany do wizyty, a sam moze miec ich przypisane kilka (warunek inna godzina)
    @Getter
    private  int id;
    @Getter
    @Setter
    private Doctor doctor;
    @Getter
    private Pet patient;
    private static int count = 1;
    @Getter
    @Setter
    private VisitInformation visitInformation;
    private Map<Integer,Doctor> asociateVisitWithDoc = new HashMap<>();

    public int generateId() {
        return count++;
    }

    public MedicalVisit(Enum<VisitType> visitType, Pet patient) {
        int idGenerate = getId();
        this.id=idGenerate;
        this.visitType = visitType;
        this.patient = patient;
        var visitInformation = new VisitInformation(this, null);
        this.visitInformation = visitInformation;
    }

    public MedicalVisit(Enum<VisitType> visitType, Pet patient, Doctor doctor) {
        this.visitType = visitType;
        this.patient = patient;
        doctors.add(doctor);
        this.doctor=doctor;
       // doctor.getVisits().add(this); //polaczenie zwrotne
       // doctor.addPatient(patient);
        asociateVisitWithDoc.put(getId(),doctor); //polaczenie zwrotne
        var visitInformation = new VisitInformation(this, doctor);
        this.visitInformation = visitInformation;
    }

    public enum VisitType {
        CONTROL, CITO, OPERATION
    }

    public Optional<Doctor> findDoctorAssosiciatedWithVisit(Doctor doctor) { // kwalifikator po stronie medicalVisti na podstawie id

        if (asociateVisitWithDoc.values().stream().anyMatch(doc -> doc.equals(doctor))) {
            return Optional.of(asociateVisitWithDoc.values().stream().filter(doc ->  doc.equals(doctor)).findAny().get());
        }

        return Optional.empty();
    }
    public void addDoctorToVisit(Doctor doctor){
        if(!doctor.getVisits().stream().anyMatch(medicalVisit -> medicalVisit.equals(this))){
            doctor.getVisits().add(this); // polaczenie zwrotne
        }
    }

}
