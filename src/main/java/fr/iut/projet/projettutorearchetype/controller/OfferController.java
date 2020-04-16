package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.models.Offer;
import fr.iut.projet.projettutorearchetype.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("offer")
public class OfferController{

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
            @PathVariable(name="id") int id
    ){
        return offerService.getOffer(id);
    }

}
