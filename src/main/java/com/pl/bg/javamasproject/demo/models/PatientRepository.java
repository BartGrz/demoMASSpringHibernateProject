package com.pl.bg.javamasproject.demo.models;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;



@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {


    List<Patient> findAll() ;
    Optional<Patient> findById(Integer id);
    Patient save(Patient entity);

    @Modifying
    @Query(nativeQuery = true , value= " INSERT INTO PATIENTS (PATIENT_NAME, ID_CLIENT, ID_CARD) values (:patientName,:id_client,:id_card )")
    @Transactional
    void saveWithParameters(@Param("patientName") String name, @Param("id_client") int id_client, @Param("id_card")int id_card);

    @Query(nativeQuery = true, value = "select count(id) >0 from PATIENTS where id=:id")
    boolean existById(@Param("id")Integer id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "Delete from PATIENTS where id=:id")
    void deleteById(@Param("id") Integer integer);
}
