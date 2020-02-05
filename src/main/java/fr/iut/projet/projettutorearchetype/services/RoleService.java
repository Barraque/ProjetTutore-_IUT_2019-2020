package fr.iut.projet.projettutorearchetype.services;

import fr.iut.projet.projettutorearchetype.models.Role;
import fr.iut.projet.projettutorearchetype.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService{

    @Autowired
    RoleRepository roleRepository;

    public Role addRole (final Role role){
        return roleRepository.save(role);
    }

    public Role getRole (final int roleId){
        Optional<Role> role = roleRepository.findById(roleId);
        return role.get();
    }

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }


}