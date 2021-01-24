package com.example.myFrontend.service;


import com.example.myFrontend.model.Agency;
import com.example.myFrontend.model.Appointment;
import com.example.myFrontend.model.Person;
import com.example.myFrontend.model.Request;
import com.example.myFrontend.repository.AgencyRepository;
import com.example.myFrontend.repository.AppointmentRepository;
import com.example.myFrontend.repository.PersonRepository;
import com.example.myFrontend.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImplementation implements PersonService {

    private final PersonRepository personRepository;
    private final AppointmentRepository appointmentRepository;
    private final RequestRepository requestRepository;
    private final AgencyRepository agencyRepository;
//    private final BCryptPasswordEncoder passwordEncoder;
//
    @Autowired
    public PersonServiceImplementation(PersonRepository personRepository,
                                       AppointmentRepository appointmentRepository,
                                       RequestRepository requestRepository,
                                       AgencyRepository agencyRepository){
//                                       BCryptPasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.appointmentRepository = appointmentRepository;
        this.requestRepository = requestRepository;
        this.agencyRepository = agencyRepository;
//        this.passwordEncoder = passwordEncoder;
    }
//
//
//    @Override
//    public String register(PersonDataTransferObject personDataTransferObject) {
//        System.out.println(personDataTransferObject.toString());
//        Person person = new Person(personDataTransferObject.getUsername(),
//                passwordEncoder.encode(personDataTransferObject.getPassword()),
//                personDataTransferObject.getAge(),
//                personDataTransferObject.getRoles(),
//                personDataTransferObject.getResidence(),
//                personDataTransferObject.getAgency()
//                );
//
//        if (person.getRoles().equals("EMPLOYEE")) {
//             if ( checkIfAgencyExists( person.getAgency()) == false) {
//
//                 person.setActive(0); // makes him inactive until agency is confirmed
//                 personRepository.save(person);
//
//                 return "Go to localhost:8080/api/createAgency to create this agency!";
//             }
//        }
//
//        if (person.getRoles().equals("CITIZEN")) {
//            person.setAgency(null);
//        }
//
//        personRepository.save(person);
//        return "Registered";
//    }
//
//    @Override
//    public void set(AppointmentDataTransferObject appointmentDataTransferObject) {
//        Appointment appointment = new Appointment(appointmentDataTransferObject.getDate(),
//                appointmentDataTransferObject.getTime(),
//                appointmentDataTransferObject.getPlace(),
//                appointmentDataTransferObject.getAgency(),
//                "pending");
//
//        Person person = loggedInUser();
//
//
//        appointment.setCitizenName(person.getUsername());
//
//        appointmentRepository.save(appointment);
//    }
//
//
    @Override
    public void cancel(String date, String time) {
        Appointment appointment = appointmentRepository.findByDateAndTime(date, time);
        appointmentRepository.delete(appointment);
    }
//
//    @Override
//    public void updateDateAndTime(String oldDate, String newDate, String oldTime, String newTime) {
//        Appointment oldAppointment = appointmentRepository.findByDateAndTime(oldDate, oldTime);
//        if (newDate == null) {
//            oldAppointment.setTime(newTime);
//        } else if (newTime == null) {
//            oldAppointment.setDate(newDate);
//        } else {
//            oldAppointment.setTime(newTime);
//            oldAppointment.setDate(newDate);
//        }
//        appointmentRepository.save(oldAppointment);
//    }
//
//    @Override
//    public Appointment viewByDateAndTime(String date, String time) {
//        return appointmentRepository.findByDateAndTime(date, time);
//    }
//

    @Override
    public List<Appointment> viewMyAppointments( String citizenName) {

//        Person person = loggedInUser();
//
//        return  appointmentRepository.findByCitizenName( person.getUsername());

        return appointmentRepository.findByCitizenName( citizenName);
    }

    @Override
    public List<Appointment> viewAllAppointments( String agency) {
        return  appointmentRepository.findByAgency( agency);
    }
//
    @Override
    public void approveAppointment(String date, String time) {
        Appointment appointment = appointmentRepository.findByDateAndTime(date, time);
        appointment.setStatus("approved");
        appointmentRepository.save(appointment);
    }
//
    @Override
    public void deleteAppointment(String date, String time) {
        Appointment appointment = appointmentRepository.findByDateAndTime(date, time);
        appointment.setStatus("canceled");
        appointmentRepository.save(appointment);
    }
//
//    @Override
//    public List<Person> viewAllCitizens() {
//        return personRepository.findAll();
//    }
//
//    @Override
//    public void setRequest(RequestDataTransferObject requestDataTransferObject) {
//
//        Optional<Person> person = Optional.ofNullable(personRepository.findByUsername(requestDataTransferObject.getNameOfEmployee()));
//
//        if (person.isEmpty()) {
//            System.out.println("Sb is trying to hack us");
//        } else {
//            Request request = new Request(requestDataTransferObject.getNameOfEmployee(),
//                    requestDataTransferObject.getAgencyName(),
//                    requestDataTransferObject.getAgencyLocation(),
//                    requestDataTransferObject.getDate(),
//                    "pending");
//            requestRepository.save(request);
//        }
//    }
//
    public List<Agency> viewAgencies() {
        //System.out.println("Get all Agencies request executed");
        return agencyRepository.findAll();
    }
//
//    public void deleteAllAgencies() {
//        //System.out.println("Delete all agencies request executed");
//        agencyRepository.deleteAll();
//        //return "All agencies deleted";
//    }
//
//
    @Override
    public List<Request> viewRequests() {
        //System.out.println("Get all Requests request executed");
        return requestRepository.findAll();
    }
//
    @Override
    public void declineRequest(String date) {
        Request request = requestRepository.findByDate(date);
        request.setStatus("declined");
        requestRepository.save(request);

    }
//
    @Override
    public void acceptRequest(String date) {
        System.out.println(date);
        Request request = requestRepository.findByDate(date);
        System.out.println(request.getAgencyName());
        request.setStatus("accepted");

        createAgency( request.getAgencyName());

        Person person = personRepository.findByAgency( request.getAgencyName());
        person.setActive(1); //makes the employee active
        System.out.println("done");
        personRepository.save(person);

        requestRepository.save(request);
    }
//
        @Override
        public List<Person> viewAllEmployees() {
            return personRepository.findByRoles( "EMPLOYEE");
        }
//
//    private boolean checkIfAgencyExists( String agencyName) {
//
//        Optional<Agency> agency = Optional.ofNullable(agencyRepository.findFirstByAgencyName(agencyName));
//
//        if ( agency.isPresent() ) {
//            return true;
//        } else {
//            return false;
//        }
//
//    }
//
    private void createAgency( String agencyName) {
        Request request = requestRepository.findByAgencyName( agencyName);

        Agency agency = new Agency(
                request.getAgencyName(),
                request.getAgencyLocation());

        agencyRepository.save(agency);

    }
//
//    private Person loggedInUser() {
//        Object principal = SecurityContextHolder.getContext()
//                .getAuthentication().getPrincipal();
//
//        String username = null;
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails)principal).getUsername();
//        } else {
//            username = principal.toString();
//        }
//
//        return personRepository.findByUsername( username);
//
//    }

}
