package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.models.Offer;
import fr.iut.projet.projettutorearchetype.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OfferController{

    @Autowired
    OfferService offerService;

    @PostMapping("offer")
    public Offer addOffer(
            @RequestBody Offer offer
    ){
        return offerService.addOffer(offer);

    }

    @GetMapping("offers")
    public List<Offer> getAllOffers(){
        return offerService.getAllOffers();
    }

    @GetMapping("offer")
    public Offer getOffer(
            @RequestParam(name="id") int id
    ){
        return offerService.getOffer(id);
    }

}
