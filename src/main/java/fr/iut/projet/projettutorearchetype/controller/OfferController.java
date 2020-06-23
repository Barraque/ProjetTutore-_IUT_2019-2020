package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.exceptions.ForbiddenException;
import fr.iut.projet.projettutorearchetype.models.Offer;
import fr.iut.projet.projettutorearchetype.models.RolesEnum;
import fr.iut.projet.projettutorearchetype.models.Tag;
import fr.iut.projet.projettutorearchetype.models.User;
import fr.iut.projet.projettutorearchetype.services.OfferService;
import fr.iut.projet.projettutorearchetype.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("offer")
@CrossOrigin(origins = "*")
public class OfferController{

    @Autowired
    UserService userService;

    @Autowired
    OfferService offerService;

    @PostMapping("")
    public Offer addOffer(
        @RequestBody Offer offer
    ){
        return offerService.addOffer(offer);

    }

    @GetMapping("all")
    public List<Offer> getAllOffers(){
        return offerService.getAllOffers();
    }

    @GetMapping("{id}")
    public Offer getOffer(
        @PathVariable int id
    ){
        return offerService.getOffer(id);
    }


    @GetMapping("{id}/tags")
    public List<Tag> retrieveUserTags(
            @PathVariable(name = "id") int id
    ){
        User requestUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(requestUser.getRoles().toArray()[0].equals(RolesEnum.DEPARTMENT_MANAGER)) && !(requestUser.getRoles().toArray()[0].equals(RolesEnum.ADMINISTRATOR))){
            throw new ForbiddenException();
        }
        return this.offerService.getOfferTags(id);
    }


    @PatchMapping("{id}/tags")
    public Offer addTagToOffer(
            @PathVariable(name = "id") int id,
            @RequestBody ArrayList<Tag> tags
    ){
        return this.offerService.addTagsToOffer(id, tags);
    }

    @PatchMapping("{id}/accept")
    public Offer acceptOffer(
            @PathVariable(name = "id") int id,
            @RequestBody User user
    ){
        return this.offerService.acceptOffer(id, user);
    }

    @GetMapping("users/{userid}")
    public Set<Offer> retrieveUserTags(
            @PathVariable(name = "userid") Integer userid) {
        List<Offer> offers = this.offerService.matchOffersByUserTags(userid);
        Set<Offer> offerSet = new HashSet<>(offers);
        return offerSet;
    }

}
