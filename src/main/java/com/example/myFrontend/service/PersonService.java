package com.example.myFrontend.service;


import com.example.myFrontend.model.Agency;
import com.example.myFrontend.model.Appointment;
import com.example.myFrontend.model.Person;
import com.example.myFrontend.model.Request;

import java.util.List;

public interface PersonService {

//    // ALL
//    String register(PersonDataTransferObject personDataTransferObject); // check TODO: ALL
//
//    // CITIZEN
//    void set(AppointmentDataTransferObject appointmentDataTransferObject); // check TODO: CITIZEN
    void cancel(String date, String time); // check TODO: CITIZEN
//    void updateDateAndTime(String oldDate, String newDate, String oldTime, String newTime); // check TODO: CITIZEN
//    Appointment viewByDateAndTime(String date, String time); //check TODO: CITIZEN
        List<Appointment> viewMyAppointments( String citizenName);

    //
//
//    // EMPLOYEE
    List<Appointment> viewAllAppointments( String agency); // TODO: EMPLOYEE
    void approveAppointment(String date, String time); // check TODO: EMPLOYEE
    void deleteAppointment(String date, String time); // check TODO: EMPLOYEE
//    List<Person> viewAllCitizens(); // check TODO: EMPLOYEE
//    void setRequest(RequestDataTransferObject requestDataTransferObject); // TODO: EMPLOYEE
//
//
//    // AGENT
    List<Request> viewRequests(); // TODO: AGENT
    void acceptRequest(String date); // TODO: AGENT
    void declineRequest(String date); // TODO: AGENT
//    void deleteAllAgencies(); // TODO: AGENT
    List<Agency> viewAgencies(); // TODO: AGENT
    List<Person> viewAllEmployees();


}