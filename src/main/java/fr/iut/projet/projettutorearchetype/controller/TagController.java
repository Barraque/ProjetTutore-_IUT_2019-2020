package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.models.Tag;
import fr.iut.projet.projettutorearchetype.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @PostMapping("tag")
    public Tag addTag(
        @RequestBody Tag tag
    )
    {
        if(tag != null)
        System.out.println("tag name : " + tag.getName());
        return tagService.addTag(tag);
    }


    @GetMapping("tag")
    public Tag getTag(
            @RequestParam(name = "id") UUID id
    )
    {
        return tagService.getTag(id);
    }
}
