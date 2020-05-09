package fr.iut.projet.projettutorearchetype.services;

import fr.iut.projet.projettutorearchetype.models.Offer;
import fr.iut.projet.projettutorearchetype.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OfferService{

    @Autowired
    OfferRepository offerRepository;

    public Offer addOffer (final Offer offer){
        return offerRepository.save(offer);
    }

    public Offer getOffer (final int offerId){
        Optional<Offer> offer = offerRepository.findById(offerId);

        if (offer.isEmpty()) {
            throw new NoSuchElementException("Unknown offer with ID [" + offerId + "]");
        }

        return offer.get();
    }

    public List<Offer> getAllOffers(){
        return offerRepository.findAll();
    }


}