package fr.iut.projet.projettutorearchetype.services;

import fr.iut.projet.projettutorearchetype.models.CV;
import fr.iut.projet.projettutorearchetype.repositories.CVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CVService {

    @Autowired
    CVRepository cvRepository;

    public CV addCV(final CV cv){

        return cvRepository.save(cv);
    }

    public CV getCV(final int cvId){
        Optional<CV> cv = cvRepository.findById(cvId);

        if (cv.isEmpty()) {
            throw new NoSuchElementException("Unknown cv with ID [" + cvId + "]");
        }

        return cv.get();
    }

    public List<CV> getAllCV(){
        return  cvRepository.findAll();
    }

    public CV getCvOfUserFromUserId(final int userId){
        Optional<CV> cv = cvRepository.findByUser_userId(userId);

        if (cv.isEmpty()) {
            throw new NoSuchElementException("Unknown cv that belong to user with ID [" + userId + "]");
        }
        return cv.get();
    }

    public CV getCvOfUserFromLogin(final String userLogin){
        Optional<CV> cv = cvRepository.findByUser_login(userLogin);

        if (cv.isEmpty()) {
            throw new NoSuchElementException("Unknown cv that belong to user with login [" + userLogin + "]");
        }
        return cv.get();
    }
}
