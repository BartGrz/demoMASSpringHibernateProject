package com.pl.bg.javamasproject.demo.MP1;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.expression.spel.ast.OpAnd;

import javax.print.Doc;
import java.util.*;
import java.util.stream.Collectors;

@ToString(callSuper = true, exclude = {"doctorId", "bonus", "visits", "patients","asociateDoctorWithVisit"})
public class Doctor extends Employee {

    @Getter
    private Enum<Specialization> spec;
    @Getter
    private Set<Pet> patients = new HashSet<>();//asocjacja zwykla
    @Getter
    private Set<MedicalVisit> visits = new HashSet<>();//asocjacja kwalifikowana
    @Getter
    private int doctorId ;
    @Getter
    private final double bonus = 0.20;
    private Map<Integer,MedicalVisit> asociateDoctorWithVisit = new HashMap<>();

    private static int count = 1;

    public Doctor(String firstName, String lastName, String PESEL, Enum<Employee.Contract> contract, Specialization specialization) {
        super(firstName, lastName, PESEL, contract);
        this.spec = specialization;
        int idGenerate = generateId();
        this.doctorId = idGenerate;
    }

    private int generateId() {
        return count++;
    }

    enum Specialization {
        SURGEON, INTERNIST, STOMATOLOGIST, ONCOLOGIST
    }

    public void addVisit(MedicalVisit visit) {//metoda obslugujaca polaczenie w ramach asocjacji kwalifikowanej
        if (!visits.stream().anyMatch(medicalVisit -> medicalVisit.getId() == visit.getId())) {
            visits.add(visit);
           // visit.getDoctors().add(this);
           // addPatient(visit.getPatient());
            visit.setVisitInformation(new VisitInformation(visit, this));
            asociateDoctorWithVisit.put(getDoctorId(),visit); //polaczenie zwrotne
        } else {
            System.out.println("visit already added");
        }
    }

    public void addPatient(Pet pet) { //metoda obslugujaca polaczenie asocjacji zwyklej

        if (!patients.stream().anyMatch(patient -> patient.getId() == pet.getId())) {
            patients.add(pet);
            pet.getMedicalCareHIstory().put(pet.getId(), this); //polaczenie zwrotne
        } else {
            System.out.println("Patient is already uder care of doctor with id " + this.getDoctorId() + " ");
        }
    }

    public Optional<MedicalVisit> findVisit(MedicalVisit visit) { //kwalifikator w tym przypadku to id wizyty - > za pomoca mapy

        if (asociateDoctorWithVisit.values().stream().anyMatch(medicalVisit -> medicalVisit.equals(visit))) {

            return Optional.of(asociateDoctorWithVisit.values().stream().filter(medicalVisit -> medicalVisit.equals(visit)).findAny().get());
        } else {
            return Optional.empty();
        }
    }

    public void addPatientToDoctor(Pet pet) {

        patients.add(pet);

    }
}
