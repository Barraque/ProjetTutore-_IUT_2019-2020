package fr.iut.projet.projettutorearchetype.services;

import fr.iut.projet.projettutorearchetype.models.Appointment;
import fr.iut.projet.projettutorearchetype.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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

        if (appointment.isEmpty()) {
            throw new NoSuchElementException("Unknown appointment with ID [" + appointmentId + "]");
        }

        return appointment.get();
    }

    public List<Appointment> getAllAppointment(){
        return  appointmentRepository.findAll();
    }

    public List<Appointment> getAllAppointmentFromUserID(final int userId){
        return appointmentRepository.findAllByUser_userId(userId);
    }

    public List<Appointment> getAllAppointmentFromUserLogin(final String userLogin){
        return appointmentRepository.findAllByUser_login(userLogin);
    };
}
