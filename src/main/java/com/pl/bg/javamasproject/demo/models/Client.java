package com.pl.bg.javamasproject.demo.models;


import com.pl.bg.javamasproject.demo.tools.Looper;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.*;

@Entity
@Table(name = "clients")
@Builder
@AllArgsConstructor
@Component
@NoArgsConstructor
public class Client   {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;
    @Column(name = "client_name")
    @Getter@Setter
    private String client_name;
    @Column(name = "client_number")
    @Getter@Setter
    private int client_number;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    @Getter@Setter
    private Set<Patient> patients ;

    public static List<String> fields(Class clazz) {

        List<String> fieldsList = new ArrayList<>();
        Field[] tab =clazz.getClass().getDeclaredFields();

        Looper.forLoop(0,tab.length,i ->fieldsList.add(tab[i].getName()));
        return fieldsList;
    }

}
