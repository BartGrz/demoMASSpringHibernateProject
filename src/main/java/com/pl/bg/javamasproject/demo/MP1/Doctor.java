package com.pl.bg.javamasproject.demo.MP1;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.expression.spel.ast.OpAnd;

import javax.print.Doc;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ToString(callSuper = true, exclude = {"doctorId", "bonus", "visits", "patients"})
public class Doctor extends Employee {

    @Getter
    private Enum<Specialization> spec;
    @Getter
    private Set<Pet> patients = new HashSet<>();//asocjacja zwykla
    @Getter
    private Set<MedicalVisit> visits = new HashSet<>();//asocjacja kwalifikowana
    @Getter
    private int doctorId = generateId();
    @Getter
    private final double bonus = 0.20;

    private static int count = 1;

    public Doctor(String firstName, String lastName, String PESEL, Enum<Employee.Contract> contract, Specialization specialization) {
        super(firstName, lastName, PESEL, contract);
        this.spec = specialization;
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
            visit.getDoctors().add(this); //polaczenie zwrotne
            addPatient(visit.getPatient());
            visit.setVisitInformation(new VisitInformation(visit, this));
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

    public Optional<MedicalVisit> findVisit(int id) { //kwalifikator w tym przypadku to id wizyty

        if (visits.stream().anyMatch(medicalVisit -> medicalVisit.getId() == id)) {

            return Optional.of(visits.stream().filter(medicalVisit -> medicalVisit.getId() == id).findAny().get());
        } else {
            return Optional.empty();
        }
    }

    public void addPatientToDoctor(Pet pet) {

        patients.add(pet);

    }
}
