package com.example.myFrontend.repository;


import com.example.myFrontend.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {

    Agency findFirstByAgencyName (String agencyName);
    //Agency findByAgencyName(String name);
    void deleteAll();
}
