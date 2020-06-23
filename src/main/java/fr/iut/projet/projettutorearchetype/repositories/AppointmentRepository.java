package fr.iut.projet.projettutorearchetype.repositories;

import fr.iut.projet.projettutorearchetype.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    //List<Appointment> findAllByUser_login(String userLogin);
    //List<Appointment> findAllByUser_userId(int userId);
}
