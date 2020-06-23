package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.models.CV;
import fr.iut.projet.projettutorearchetype.services.CVService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("cv")
@Slf4j
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

    @PatchMapping("{id}")
    public CV patchCv(
            @RequestBody byte[] cv,
            @PathVariable int id
    ){
        CV originalCv = this.cvService.getCV(id);
        return cvService.patchCvFile(originalCv, cv);
    }

    @GetMapping("user/{id}")
    public CV findByUser_userId(
            @PathVariable int id
    ){
        return cvService.getCvOfUserFromUserId(id);
    }

    @PatchMapping("{id}/approve")
    public CV approveCv(
            @PathVariable int id
    ){
        System.out.println("approving");
        CV cv = this.cvService.getCV(id);
        return cvService.approveCv(cv);
    }

    @PatchMapping("{id}/reject")
    public CV rejectCv(
            @PathVariable int id
    ){
        CV cv = this.cvService.getCV(id);
        return cvService.rejectCv(cv);
    }
}
