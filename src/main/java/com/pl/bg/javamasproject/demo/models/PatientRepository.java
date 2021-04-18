package com.pl.bg.javamasproject.demo.models;

import com.pl.bg.javamasproject.demo.models.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface PatientRepository{

    List<Patient> findAll() ;
    Optional<Patient> findById(Integer id);
    void save(Patient entity);

}
