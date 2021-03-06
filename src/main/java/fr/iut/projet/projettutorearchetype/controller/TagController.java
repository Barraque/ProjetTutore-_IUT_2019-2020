package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.models.Tag;
import fr.iut.projet.projettutorearchetype.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    TagService tagService;

    @PostMapping("tag")
    public Tag addTag(
            @RequestBody Tag tag
    ){
        if (tag != null){
            System.out.println(tag.toString());
        }
        return tagService.addTag(tag);
    }


    @GetMapping("tags")
    public List<Tag> getAllTag(){
        return tagService.getAllTags();
    }

    @GetMapping("tag")
    public Tag getTag(
            @RequestParam(name = "id") int id
    ){
        return tagService.getTag(id);
    }


}
