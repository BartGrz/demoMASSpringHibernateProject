package com.pl.bg.javamasproject.demo.MP1;

import javafx.fxml.Initializable;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * Poasiada "klucze" wizyty i lekarza dzieki czemu mozna sie dowiedziec o szczegolach wizyty
 */
public class VisitInformation { //asocjacja z atrybutem

    private MedicalVisit medicalVisit;
    private Doctor doctor;
    private double price;
    private double doctorShare;

    public VisitInformation(MedicalVisit medicalVisit, Doctor doctor) {

        prices.put(MedicalVisit.VisitType.CITO, 100.00);
        prices.put(MedicalVisit.VisitType.OPERATION, 300.00);
        prices.put(MedicalVisit.VisitType.CONTROL, 50.0);

        this.medicalVisit = medicalVisit;
        this.price = prices.get(medicalVisit.getVisitType());
        if (doctor != null) {
            this.doctor = doctor;
            this.doctorShare = price * doctor.getBonus();
        }
    }

    private Map<Enum<MedicalVisit.VisitType>, Double> prices = new HashMap<>();


    @Override
    public String toString() {

        return "VisitInformation{visitType : " + medicalVisit.getVisitType().toString() +
                " price=" + price +
                ", doctorShare=" + doctorShare +
                " doctor spec " + doctor.getSpec().toString() + '}';
    }
}
