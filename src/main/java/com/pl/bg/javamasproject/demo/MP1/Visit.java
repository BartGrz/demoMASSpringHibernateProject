package com.pl.bg.javamasproject.demo.MP1;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * klasa visit moze istniec bez wewnetrznej klasy Involved, natomiast klasa wew (czesc) nie istnieje bez calosci : kompozycja
 */
public class Visit { //kompozycja dodac zwrotnosc (klasa wewnetrzna musi miec pol zwrotne

    private List<Involved> visit = new ArrayList<>();
    private LocalDateTime localDateTime = LocalDateTime.now();

    public Visit(LocalDateTime localDateTime) {

        this.localDateTime = localDateTime;

    }
        public Involved addInvolved (Enum < StaffType > val) {
            Involved involved = new Involved(val);
            visit.add(involved);
            involved.getVisit().add(this); // polaczenie zwrotne
            return involved;

        }

        public class Involved {

            private Enum<StaffType> involved;
            @Getter
            private List<Visit> visit = new ArrayList<>();

            public Involved(Enum<StaffType> involved) {
                this.involved = involved;
            }
        }

        public enum StaffType {
            DOCTOR, PATIENT, CLIENT
        }
    }
