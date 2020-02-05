package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.models.Role;
import fr.iut.projet.projettutorearchetype.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController{

    @Autowired
    RoleService roleService;

    @PostMapping("role")
    public Role addRole(
            @RequestBody Role role
    ){
        return roleService.addRole(role);

    }

    @GetMapping("roles")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("role")
    public Role getRole(
            @RequestParam(name="id") int id
    ){
        return roleService.getRole(id);
    }


}
