package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.models.Role;
import fr.iut.projet.projettutorearchetype.models.Tag;
import fr.iut.projet.projettutorearchetype.models.User;
import fr.iut.projet.projettutorearchetype.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("user")
    public User addUser(
            @RequestBody User user
    ){
        user.setFirstConnexion(1);
        userService.createPassword(user);
        if (user != null){
            System.out.println(user.toString());
        }
        return userService.addUser(user);
    }


    @GetMapping("users")
    public List<User> getAllTag(){
        return userService.getAllUsers();
    }

    @GetMapping("user")
    public User getuser(
            @RequestParam(name = "id") int id
    ){
        return userService.getUser(id);
    }


}