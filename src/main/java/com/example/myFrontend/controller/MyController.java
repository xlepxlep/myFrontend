package com.example.myFrontend.controller;

import com.example.myFrontend.model.Agency;
import com.example.myFrontend.model.Person;
import com.example.myFrontend.model.Request;
import com.example.myFrontend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MyController {

    private final PersonService myService;

    @Autowired
    public MyController(PersonService myService) {
        this.myService = myService;
    }

    @GetMapping("/api/employee/viewAppointments")
    public String viewAppointments( Model model) {
        model.addAttribute("appointments", myService.viewAllAppointments( "IKA"));
        return "viewAppointments";
    }

    @GetMapping("/href")
    public String href() {
        return "href";
    }

    @GetMapping("/api/citizen/viewAppointments")
    public String viewMyAppointments( Model model) {
        model.addAttribute("appointments", myService.viewMyAppointments( "nick"));
        return "viewMyAppointments";
    }

    @GetMapping("api/admin/requests/view")
    public String viewRequests( Model model) {

        model.addAttribute("requests", myService.viewRequests());
        return "viewRequests";
    }

    @GetMapping("api/admin/agencies/view")
    public String getAllAgencies( Model model) {
        model.addAttribute("agencies", myService.viewAgencies());
        return "viewAgencies";
    }

    @GetMapping("api/admin/employees/view")
    public String getAllEmployees( Model model) {
        model.addAttribute("employees", myService.viewAllEmployees());
        return "viewEmployees";
    }

    @GetMapping("api/employee/approveAppointment/{date}/{time}")
    public String approveAppointment(@PathVariable("date") String date,@PathVariable("time") String time, Model model) {
        myService.approveAppointment( date, time);
        return "approveAppointment";
    }

    @GetMapping("api/employee/declineAppointment/{date}/{time}")
    public String declineAppointment(@PathVariable("date") String date,@PathVariable("time") String time, Model model) {
        myService.deleteAppointment( date, time);
        return "declineAppointment";
    }

    @GetMapping("/api/citizen/cancelAppointment/{date}/{time}")
    public String cancelAppointment(@PathVariable("date") String date,@PathVariable("time") String time, Model model) {
        myService.cancel( date, time);
        return "cancelAppointment";
    }

    //approves a request(= creates a new agency and deletes this request)
    @GetMapping("api/admin/approve/{date}")
    public String approveRequest(@PathVariable("date") String date, Model model) {
        myService.acceptRequest(date);
        return "approveRequest";
    }

    //declines a request
    @GetMapping("api/admin/delete/{date}")
    public String declineRequest(@PathVariable("date") String date) {
        myService.declineRequest(date);
        return "declineRequest";
    }

    @GetMapping("api/admin/viewMenu")
    public String declineRequest() {
        return "viewMenu";
    }

}
