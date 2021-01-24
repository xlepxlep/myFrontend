package com.example.myFrontend.repository;


import com.example.myFrontend.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAll();
    Request findByAgencyName( String agencyName);
    Request findByDate(String date);
}
