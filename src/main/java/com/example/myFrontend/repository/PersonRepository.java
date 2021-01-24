package com.example.myFrontend.repository;


import com.example.myFrontend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

//    List<Citizen> findAll();
//    Citizen findByEmail(String email);
    Person findByAgency( String agency);
//    Person findByUsername(String firstName);
    List<Person> findByRoles(String role);
}
