package fr.iut.projet.projettutorearchetype.services;

import fr.iut.projet.projettutorearchetype.models.Department;
import fr.iut.projet.projettutorearchetype.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public Department addDepartment(final Department department){
        return departmentRepository.save(department);
    }

    public Department getDepartment(final int departmentId){
        Optional<Department> department = departmentRepository.findById(departmentId);
        return department.get();
    }

    public List<Department> getAllDepartment(){
        return  departmentRepository.findAll();
    }

}
