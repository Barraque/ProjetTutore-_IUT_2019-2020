package fr.iut.projet.projettutorearchetype.services;

import fr.iut.projet.projettutorearchetype.models.User;
import fr.iut.projet.projettutorearchetype.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUser(final User user) {
        return userRepository.save(user);
    }

    public User getUser(final int userId) {
        Optional<User> user = userRepository.findById(userId);

        return user.get();
    }
    public void createPassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
