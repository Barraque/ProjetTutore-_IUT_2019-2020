package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.models.Appointment;
import fr.iut.projet.projettutorearchetype.models.AppointmentDAO;
import fr.iut.projet.projettutorearchetype.models.Offer;
import fr.iut.projet.projettutorearchetype.models.User;
import fr.iut.projet.projettutorearchetype.services.AppointmentService;
import fr.iut.projet.projettutorearchetype.services.OfferService;
import fr.iut.projet.projettutorearchetype.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    UserService userService;

    @Autowired
    OfferService offerService;

    @PostMapping("")
    public Appointment addAppointment(
            @RequestBody AppointmentDAO appointment
    ){
        User user = userService.getUser(appointment.getUserid());
        Offer offer = offerService.getOffer(appointment.getOfferid());
        Appointment app = new Appointment();
        app.setStartTime(appointment.getStartTime());
        app.setOffer(offer);
        app.setUser(user);
        this.appointmentService.addAppointment(app);
        return app;
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

    /*@GetMapping("user/{id}")
    public List<Appointment> findByUser_userId(
            @PathVariable int id
    ){
        return appointmentService.getAllAppointmentFromUserID(id);
    }*/
}
