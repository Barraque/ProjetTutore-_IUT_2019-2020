package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.models.Appointment;
import fr.iut.projet.projettutorearchetype.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("")
    public Appointment addAppointment(
            @RequestBody Appointment appointment
    ){
        return this.appointmentService.addAppointment(appointment);
    }

    @GetMapping("all")
    public List<Appointment> getAllAppointment(){
        return appointmentService.getAllAppointment();
    }

    @GetMapping("{id}")
    public Appointment getAppointment(
            @PathVariable int id
    ){
        return appointmentService.getAppointment(id);
    }

    @GetMapping("user/{id}")
    public Appointment findByUser_userId(
            @PathVariable int id
    ){
        return appointmentService.findByUser_userId(id);
    }
}
