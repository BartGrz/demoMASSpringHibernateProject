package com.pl.bg.javamasproject.demo.Beans;

import com.pl.bg.javamasproject.demo.SQL.InsertQueryBuilder;
import com.pl.bg.javamasproject.demo.SQL.SelectQueryBuilder;
import com.pl.bg.javamasproject.demo.models.Patient;
import com.pl.bg.javamasproject.demo.models.PatientRepository;

import java.util.List;
import java.util.Optional;


public class PatientService implements PatientRepository {

    @Override
    public List<Patient> findAll() {


        return new SelectQueryBuilder.Builder<Patient,Class>()
                .build()
                .GenerateBasicSelectResult(Patient.class);
    }

    @Override
    public Optional<Patient> findById(Integer id) {

       List<Patient> list =  new SelectQueryBuilder.Builder<Patient,Class>()
               .where(Patient.fieldsNames.ID)
               .equal(id)
               .build()
               .GenerateBasicSelectResult(Patient.class);

       if(list.isEmpty()) {
           return Optional.empty();
       }
       return Optional.of(list.get(0));

    }

    @Override
    public void save(Patient entity) {

        new InsertQueryBuilder.Builder<Patient>().insertInto(Patient.class)
                .fields(Patient.getListOfTableFields())
                .value(entity.getPatient_name())
                .value(entity.getId_card())
                .value(entity.getId_client())
                .end().generateAndExecuteSQL();

    }

}
