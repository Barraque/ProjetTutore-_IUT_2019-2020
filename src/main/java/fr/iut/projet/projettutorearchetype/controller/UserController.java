package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.constants.Constants;
import fr.iut.projet.projettutorearchetype.models.User;
import fr.iut.projet.projettutorearchetype.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(Constants.apiConstant+"user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("")
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


    @GetMapping("all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User getuser(
            @PathVariable int id
    ){
        return userService.getUser(id);
    }

    /*@PreAuthorize("hasAnyAuthority('MANAGER')")
    @DeleteMapping("user")
    public void deleteUser(
            @RequestParam(name = "id") int id
    ){
        userService.deleteUser(id);
        return;
    }*/
    //Dont know how to test

}
