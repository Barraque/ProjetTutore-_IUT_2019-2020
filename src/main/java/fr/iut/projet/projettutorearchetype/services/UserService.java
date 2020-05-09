package fr.iut.projet.projettutorearchetype.services;

import fr.iut.projet.projettutorearchetype.models.User;
import fr.iut.projet.projettutorearchetype.models.UserDAO;
import fr.iut.projet.projettutorearchetype.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUser(final User user) {
        return userRepository.save(user);
    }

    public User getUser(final int userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new NoSuchElementException("Unknow user with ID [" + userId + "]");
        }

        return user.get();
    }

    public User getUserByLogin(final String login) {
        Optional<User> user = userRepository.findByLogin(login);

        if (user.isEmpty()) {
            throw new NoSuchElementException("Unknow user with login [" + login + "]");
        }

        return user.get();
    }
    public void createPassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public UserDAO changeUser(int id, UserDAO userDAO){
        
        Optional<User> theUser = userRepository.findById(id);

        if(theUser.isEmpty()){
            throw new NoSuchElementException("Unknow user with ID [" + id + "]");
        }

        if(userDAO.getMail() != null){
            theUser.get().setMail(userDAO.getMail());
        }
        if (userDAO.getPassword() != null ){
            theUser.get().setPassword(userDAO.getPassword());
        }
        if (userDAO.getFirstname() != null){
            theUser.get().setFirstname(userDAO.getFirstname());
        }
        if (userDAO.getLastname() != null ) {
            theUser.get().setLastname(userDAO.getLastname());
        }
        // toDo : Do we allowed this ?
        /*if (userDAO.getRole() != null ) {
            theUser.get().setRole(userDAO.getRole());
        }
        if (userDAO.getDepartment_number() != null ) {
            theUser.get().setDepartment_number(userDAO.getDepartment_number());
        }*/
        userRepository.save(theUser.get());
        return theUser.get().convertToUserDAO();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByLogin(username);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException(username));

        return optionalUser
                .map(User::new).get();
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
