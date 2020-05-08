package fr.iut.projet.projettutorearchetype.services;

import fr.iut.projet.projettutorearchetype.models.Appointment;
import fr.iut.projet.projettutorearchetype.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public Appointment addAppointment(final Appointment appointment){

        return appointmentRepository.save(appointment);
    }

    public Appointment getAppointment(final int appointmentId){
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        return appointment.get();
    }

    public List<Appointment> getAllAppointment(){
        return  appointmentRepository.findAll();
    }

    public Appointment findByUser_userId(final int userId){
        Optional<Appointment> appointment = appointmentRepository.findByUser_userId(userId);
        return appointment.get();
    }

    public Appointment findByUser_login(final String userLogin){
        Optional<Appointment> appointment = appointmentRepository.findByUser_login(userLogin);
        return appointment.get();
    }
}
