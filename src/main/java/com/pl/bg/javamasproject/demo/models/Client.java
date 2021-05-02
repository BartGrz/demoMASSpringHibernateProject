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
public class Client extends EntityTemplate {

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

    public Client() {
    }


    public  List<String> allFields () {

        List<String> fieldsList = new ArrayList<>();
        Field[] tab =this.getClass().getDeclaredFields();
        Looper.forLoop(0,tab.length, i -> fieldsList.add(tab[i].getName()));
        return fieldsList;
    }

    public static List<String> getListOfTableFields() {

        List<String> fieldsList = new ArrayList<>();
        Field[] tab =Client.class.getClass().getDeclaredFields();

        Looper.forLoop(1,tab.length,i -> {
            for (int j = 0; j < listColumns.size(); j++) {
                if (!tab[i].getType().getSimpleName().equals(listColumns.get(j))) {
                } else {
                    fieldsList.add(tab[i].getName());
                }
            }
        });
        return fieldsList;
    }
    @Override
    public EnumSet fieldsEnum() {

        return EnumSet.allOf(Client.fieldsNames.class);
    }
    public enum fieldsNames {

       ID,CLIENT_NAME,CLIENT_NUMBER,PATIENTS
    }

    @PostConstruct
    public void validate() {

        List<String> list = new ArrayList<>(allFields());
        boolean[] tab = new boolean[list.size()];

        int index = 0;
        for (Iterator it = fieldsEnum().iterator(); it.hasNext(); ) {
            String enumField = it.next().toString().toLowerCase();
            if (list.get(index).equals(enumField)) {
                tab[index] = true;
            } else {
                tab[index] = false;
            }
            index += 1;
        }
        for (int i = 0; i<tab.length;i++) {

            if (tab[i]) {

            }else {
                logger.error("NOT ALL FIELDS FROM CLASS ARE ADDED TO ENUM CLASS, fields missing : " + list.get(i));
                break;
            }
        }
        logger.info("VALIDATION OK");
    }


}
