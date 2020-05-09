package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.constants.Constants;
import fr.iut.projet.projettutorearchetype.models.Tag;
import fr.iut.projet.projettutorearchetype.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(Constants.apiConstant+"tag")
public class TagController {

    @Autowired
    TagService tagService;

    @PostMapping("")
    public Tag addTag(
            @RequestBody Tag tag
    ){
        if (tag != null){
            System.out.println(tag.toString());
        }
        return tagService.addTag(tag);
    } 


    @GetMapping("all")
    public List<Tag> getAllTag(){
        return tagService.getAllTags();
    }

    @GetMapping("{id}")
    public Tag getTag(
            @PathVariable int id
    ){
        return tagService.getTag(id);
    }



}
