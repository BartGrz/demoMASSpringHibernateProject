package com.pl.bg.javamasproject.demo.MP1;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.OpAnd;

import javax.print.Doc;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Doctor {

    @Getter
    private final Enum<Specialization> spec;
    @Getter
    private Set<MedicalVisit> visits = new HashSet<>();//asocjacja kwalifikowana
    @Getter
    private Set<Pet> patients = new HashSet<>();//asocjacja zwykla
    @Getter
    private int doctorId = generateId();
    @Getter private final double bonus  = 0.20;

    private static int count = 1;

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
        } else {
            System.out.println("visit already added");
        }
    }
    public void addPatient(Pet pet) { //metoda obslugujaca polaczenie asocjacji zwyklej

        if (!patients.stream().anyMatch(patient -> patient.getId() == pet.getId())) {
            patients.add(pet);
            pet.getMedicalCareHIstory().put(pet.getId(),this); //polaczenie zwrotne
        } else{
            System.out.println("Patient is already uder care of doctor with id " + this.getDoctorId() + " ");
        }
    }

    public Optional<MedicalVisit> findVisit(int id) { //kwalifikator w tym przypadku to id wizyty

        if (visits.stream().anyMatch(medicalVisit -> medicalVisit.getId() == id)) {

            return Optional.of(visits.stream().filter(medicalVisit -> medicalVisit.getId()==id).findAny().get());
        } else {
            return Optional.empty();
        }

    }


    public void addPatientToDoctor(Pet pet) {

        patients.add(pet);

    }

    public static void main(String[] args) {

        Doctor doctor = new Doctor(Specialization.SURGEON);

        MedicalVisit medicalVisit = new MedicalVisit(MedicalVisit.VisitType.OPERATION);
        MedicalVisit medicalVisit1 = new MedicalVisit(MedicalVisit.VisitType.CONTROL);
        doctor.addVisit(medicalVisit);
        doctor.addVisit(medicalVisit);
        doctor.addVisit(medicalVisit1);
        System.out.println(doctor.findVisit(1).get().getDoctors().stream().filter(doctor1 -> doctor1.getDoctorId()== doctor.doctorId).findAny());
        System.out.println(doctor.findVisit(2));

    }

}
