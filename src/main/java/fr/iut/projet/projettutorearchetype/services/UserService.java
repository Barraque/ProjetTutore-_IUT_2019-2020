package fr.iut.projet.projettutorearchetype.services;

import fr.iut.projet.projettutorearchetype.models.RolesEnum;
import fr.iut.projet.projettutorearchetype.models.Tag;
import fr.iut.projet.projettutorearchetype.models.User;
import fr.iut.projet.projettutorearchetype.models.UserDAO;
import fr.iut.projet.projettutorearchetype.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TagService tagService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUser(final User user) {
        return userRepository.save(user);
    }

    public User getUser(final int userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new NoSuchElementException("Unknown user with ID [" + userId + "]");
        }

        return user.get();
    }

    public User getUserByLogin(final String login) {
        Optional<User> user = userRepository.findByLogin(login);

        if (user.isEmpty()) {
            throw new NoSuchElementException("Unknown user with login [" + login + "]");
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
            throw new NoSuchElementException("Unknown user with ID [" + id + "]");
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


    public List<Tag> getUserTags(final int userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new NoSuchElementException("Unknown user with ID [" + userId + "]");
        }

        return user.get().getTags();
    }

    public User addTagsToUser(int userId, ArrayList<Tag> tags) {

        tagService.fillTagList(tags);

        User user = this.userRepository.getOne(userId);

        List<Tag> userTags = user.getTags();
        userTags.clear();
        userTags.addAll(tags);
        user.setTags(userTags);

        return this.userRepository.save(user);
    }
    public List<User> filter(Integer department, Integer roleId, String firstname, String lastname, String username) {
        if (firstname == null) {
            firstname = "";
        }
        if (lastname == null) {
            lastname = "";
        }
        if (username == null) {
            username = "";
        }

        if (department != null && department > 0 && roleId != null) {
            RolesEnum role = RolesEnum.values()[roleId];
            return this.userRepository.findAllByFirstnameContainsAndLastnameContainsAndLoginContainsAndRoleAndDepartmentNumber_DepartmentId(firstname, lastname, username, role, department);
        } else if (department != null && department > 0) {
            return this.userRepository.findAllByFirstnameContainsAndLastnameContainsAndLoginContainsAndDepartmentNumber_DepartmentId(firstname, lastname, username, department);
        } else if (roleId != null) {
            RolesEnum role = RolesEnum.values()[roleId];
            return this.userRepository.findAllByFirstnameContainsAndLastnameContainsAndLoginContainsAndRole(firstname, lastname, username, role);
        }

        return this.userRepository.findAllByFirstnameContainsAndLastnameContainsAndLoginContains(firstname, lastname, username);
    }
}
