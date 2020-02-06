package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.models.Department;
import fr.iut.projet.projettutorearchetype.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping("department")
    public Department addDepartment(
        @RequestBody Department department
        ){
        return this.departmentService.addDepartment(department);
    }

    @GetMapping("departments")
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartment();
    }

    @GetMapping("department")
    public Department getDepartment(
            @RequestParam(name = "id") int departmentId
    ){
        return departmentService.getDepartment(departmentId);
    }

}
