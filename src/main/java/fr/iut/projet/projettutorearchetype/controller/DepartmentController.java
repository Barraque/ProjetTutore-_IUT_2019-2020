package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.models.Department;
import fr.iut.projet.projettutorearchetype.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.apiConstant+"department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping("")
    public Department addDepartment(
        @RequestBody Department department
        ){
        return this.departmentService.addDepartment(department);
    }

    @GetMapping("all")
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartments();
    }

    @GetMapping("{id}")
    public Department getDepartment(
            @PathVariable int id
    ){
        return departmentService.getDepartment(id);
    }

}
