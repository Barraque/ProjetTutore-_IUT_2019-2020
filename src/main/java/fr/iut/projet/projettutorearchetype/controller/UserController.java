package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.Exceptions.ForbiddenException;
import fr.iut.projet.projettutorearchetype.models.RolesEnum;
import fr.iut.projet.projettutorearchetype.models.User;
import fr.iut.projet.projettutorearchetype.models.UserDAO;
import fr.iut.projet.projettutorearchetype.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("")
    public User addUser(
            @RequestBody UserDAO userdao
    ){
        User requestUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User(userdao);

        if(!(requestUser.getRoles().toArray()[0].equals(RolesEnum.DEPARTMENT_MANAGER))){
            throw new ForbiddenException();
        }

        user.setFirtConnection(1);
        userService.createPassword(user);
        return userService.addUser(user);
    }


    @GetMapping("all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public UserDAO getuser(
            @PathVariable(name = "id") int id
            ){
        User requestUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(requestUser.getLogin());
        User theUser = userService.getUser(id);
        if(!theUser.getLogin().equals(requestUser.getLogin()) && !(requestUser.getRoles().toArray()[0].equals(RolesEnum.DEPARTMENT_MANAGER))){
            throw new ForbiddenException();
        }
        return userService.getUser(id).convertToUserDAO();
    }

    @GetMapping("me")
    public UserDAO getMyUser(){
        User requestUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return requestUser.convertToUserDAO();
    }

    @PatchMapping("{id}")
    public UserDAO changeUser(
            @PathVariable(name = "id") int id,
            @RequestBody UserDAO userDAO
    ){
        User requestUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = userService.getUser(id);
        if( !theUser.getLogin().equals(requestUser.getLogin()) && !(requestUser.getRoles().toArray()[0].equals(RolesEnum.ADMINISTRATOR))){
            throw new ForbiddenException();
        }
        return userService.changeUser(id,userDAO);
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
