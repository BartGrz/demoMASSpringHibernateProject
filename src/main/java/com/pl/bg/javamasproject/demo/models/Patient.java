package com.pl.bg.javamasproject.demo.models;

import com.pl.bg.javamasproject.demo.tools.Looper;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.*;


@Entity
@Table(name = "patients")
@Component
@NoArgsConstructor
@ToString(exclude = "client")
public class Patient   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;
    @Getter
    @Column(name = "patient_name")
    private String patient_name;
    @Getter
    @Column(name = "id_card")
    private int id_card;
    @Getter
    @Column(name = "id_client")
    private int id_client;
    @Getter
    @ManyToOne
    @JoinColumn(name = "id_client", insertable = false, updatable = false)
    private Client client;


    public void updateFrom(Patient source) {
        this.patient_name = source.patient_name;
        this.id_client = source.id_client;
        this.id_card=source.id_card;
    }

}

