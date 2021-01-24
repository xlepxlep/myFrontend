package com.example.myFrontend.repository;

import com.example.myFrontend.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByAgency(String agency);
    List<Appointment> findByCitizenName(String citizenName);
    Appointment findByDateAndTime( String date, String time);
    //void deleteByDateAndTime(String date, String time);
}
