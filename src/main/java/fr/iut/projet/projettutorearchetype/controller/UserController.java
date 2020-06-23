package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.exceptions.ForbiddenException;
import fr.iut.projet.projettutorearchetype.models.RolesEnum;
import fr.iut.projet.projettutorearchetype.models.Tag;
import fr.iut.projet.projettutorearchetype.models.User;
import fr.iut.projet.projettutorearchetype.models.UserDAO;
import fr.iut.projet.projettutorearchetype.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("")
    public User addUser(
            @RequestBody UserDAO userdao
    ){
        User requestUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User(userdao);

        if(!(requestUser.getRoles().toArray()[0].equals(RolesEnum.DEPARTMENT_MANAGER) || requestUser.getRoles().toArray()[0].equals(RolesEnum.ADMINISTRATOR))){
            throw new ForbiddenException();
        }

        user.setFirstConnection(1);
        userService.createPassword(user);
        return userService.addUser(user);
    }


    @GetMapping("")
    public List<User> getAllUsers(
            @RequestParam(name="department", required = false) Integer departmentId,
            @RequestParam(name="role", required = false) Integer roleId,
            @RequestParam(name="firstname", required = false) String firstname,
            @RequestParam(name="lastname", required = false) String lastname,
            @RequestParam(name="username", required = false) String username
     ){
        List<User> users = null;

        return userService.filter(departmentId, roleId, firstname, lastname, username);
    }

    @GetMapping("{id}")
    public UserDAO retrieveUserById(
            @PathVariable(name = "id") int id
            ){
        User requestUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = userService.getUser(id);
        if(!theUser.getLogin().equals(requestUser.getLogin()) && !(requestUser.getRoles().toArray()[0].equals(RolesEnum.DEPARTMENT_MANAGER)) && !(requestUser.getRoles().toArray()[0].equals(RolesEnum.ADMINISTRATOR))){
           throw new ForbiddenException();
        }
        return userService.getUser(id).convertToUserDAO();
    }

    @GetMapping("{id}/tags")
    public List<Tag> retrieveUserTags(
            @PathVariable(name = "id") int id
    ){
        User requestUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = userService.getUser(id);
        if(!theUser.getLogin().equals(requestUser.getLogin()) && !(requestUser.getRoles().toArray()[0].equals(RolesEnum.DEPARTMENT_MANAGER)) && !(requestUser.getRoles().toArray()[0].equals(RolesEnum.ADMINISTRATOR))){
            throw new ForbiddenException();
        }
        return userService.getUserTags(id);
    }


    @PatchMapping("{id}/tags")
    public User addTagToUser(
            @PathVariable(name = "id") int id,
            @RequestBody ArrayList<Tag> tags
    ){
        return this.userService.addTagsToUser(id, tags);
    }


    @GetMapping("me")
    public UserDAO getConnectedUser(){
        User requestUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.getUserByLogin(requestUser.getLogin());
        return user.convertToUserDAO();
    }

    @GetMapping("me/tags")
    public List<Tag> retrieveConnectedUserTags() {
        User requestUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.getUserByLogin(requestUser.getLogin());
        return user.getTags();
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
