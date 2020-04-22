package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.constants.Constants;
import fr.iut.projet.projettutorearchetype.models.CV;
import fr.iut.projet.projettutorearchetype.services.CVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.apiConstant+"cv")
public class CVController {

    @Autowired
    CVService cvService;

    @PostMapping("")
    public CV addCV(
            @RequestBody CV cv
    ){
        return this.cvService.addCV(cv);
    }

    @GetMapping("all")
    public List<CV> getAllCV(){
        return cvService.getAllCV();
    }

    @GetMapping("{id}")
    public CV getCV(
            @PathVariable int id
    ){
        return cvService.getCV(id);
    }

    @GetMapping("user/{id}")
    public CV findByUser_userId(
            @PathVariable int id
    ){
        return cvService.findByUser_userId(id);
    }

    @GetMapping("user/{login}")
    public CV findByUser_userLogin(
            @PathVariable String login
    ){
        return cvService.findByUser_login(login);
    }
}
