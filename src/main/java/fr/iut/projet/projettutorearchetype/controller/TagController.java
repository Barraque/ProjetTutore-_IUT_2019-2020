package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.constants.Constants;
import fr.iut.projet.projettutorearchetype.models.Tag;
import fr.iut.projet.projettutorearchetype.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TagController {

    @Autowired
    TagService tagService;

    @PostMapping(Constants.apiConstant+"tag")
    public Tag addTag(
            @RequestBody Tag tag
    ){
        if (tag != null){
            System.out.println(tag.toString());
        }
        return tagService.addTag(tag);
    }


    @GetMapping(Constants.apiConstant+"tags")
    public List<Tag> getAllTag(){
        return tagService.getAllTags();
    }

    @GetMapping(Constants.apiConstant+"tag")
    public Tag getTag(
            @RequestParam(name = "id") int id
    ){
        return tagService.getTag(id);
    }


}
