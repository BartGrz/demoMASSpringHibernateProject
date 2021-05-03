package com.pl.bg.javamasproject.demo.models;

import com.pl.bg.javamasproject.demo.models.Client;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    @Override
    List<Client> findAll();

    @Query("select p.patient_name from Patient p")
    List<String> findAllPatientsNames();

    @Override
    <S extends Client> S save(S s);

    @Override
    Optional<Client> findById(Integer integer);

    @Query("select p from Patient p")
    List<Patient>findAllPatients();

    @Query("select p from Patient p join Client c on p.id_client = c.id where c.id = :id")
    List<Patient>findAllPatientsByClientId(@Param("id")Integer id);
}
