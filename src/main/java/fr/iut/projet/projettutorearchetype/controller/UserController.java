package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.Exceptions.ForbiddenException;
import fr.iut.projet.projettutorearchetype.models.RolesEnum;
import fr.iut.projet.projettutorearchetype.models.User;
import fr.iut.projet.projettutorearchetype.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("user")
    public User addUser(
            @RequestBody User user,
            @AuthenticationPrincipal User requestUser
    ){
        if(!(requestUser.getRoles().toArray()[0].equals(RolesEnum.DEPARTMENT_MANAGER))){
            throw new ForbiddenException();
        }
        user.setFirstConnexion(1);
        userService.createPassword(user);
        return userService.addUser(user);
    }


    @GetMapping("users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("user")
    public User getuser(
            @RequestParam(name = "id") int id,
            @AuthenticationPrincipal User requestUser
            ){
        User theUser = userService.getUser(id);
        if(!theUser.getLogin().equals(requestUser.getLogin()) && !(requestUser.getRoles().toArray()[0].equals(RolesEnum.DEPARTMENT_MANAGER))){
            throw new ForbiddenException();
        }
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
