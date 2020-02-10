package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.Exceptions.ForbiddenException;
import fr.iut.projet.projettutorearchetype.models.RolesEnum;
import fr.iut.projet.projettutorearchetype.models.User;
import fr.iut.projet.projettutorearchetype.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("user")
    public User addUser(
            @RequestBody User user
    ){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(((User) principal).getRoles().toArray()[0].equals(RolesEnum.MANAGER))){
            throw new ForbiddenException();
        }
        user.setFirstConnexion(1);
        userService.createPassword(user);
        if (user != null){
            System.out.println(user.toString());
        }
        return userService.addUser(user);
    }


    @GetMapping("users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("user")
    public User getuser(
            @RequestParam(name = "id") int id
            ){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = userService.getUser(id);
        if(!theUser.getLogin().equals(((User)principal).getLogin()) && !(((User) principal).getRoles().toArray()[0].equals(RolesEnum.MANAGER))){
            throw new ForbiddenException();
        }
        return userService.getUser(id);
    }


}
