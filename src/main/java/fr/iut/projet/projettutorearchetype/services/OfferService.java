package fr.iut.projet.projettutorearchetype.services;

import fr.iut.projet.projettutorearchetype.models.Department;
import fr.iut.projet.projettutorearchetype.models.Offer;
import fr.iut.projet.projettutorearchetype.models.Tag;
import fr.iut.projet.projettutorearchetype.models.User;
import fr.iut.projet.projettutorearchetype.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OfferService{

    @Autowired
    TagService tagService;

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    UserService userService;

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

    public List<Tag> getOfferTags(int offerId) {
        Optional<Offer> offer = offerRepository.findById(offerId);

        if (offer.isEmpty()) {
            throw new NoSuchElementException("Unknown offer with ID [" + offerId + "]");
        }

        return offer.get().getTags();
    }

    public Offer addTagsToOffer(int userId, ArrayList<Tag> tags) {

        tagService.fillTagList(tags);

        Offer offer = this.offerRepository.getOne(userId);

        List<Tag> offerTags = offer.getTags();
        offerTags.clear();
        offerTags.addAll(tags);
        offer.setTags(offerTags);

        return this.offerRepository.save(offer);
    }


    public Offer acceptOffer(int offerId, User user) {
        Offer offer = this.offerRepository.getOne(offerId);
        Set<Department> acceptedDepartment = offer.getAcceptedDepartment();
        acceptedDepartment.add(user.getDepartmentNumber());
        this.offerRepository.save(offer);
        return offer;
    }

    public List<Offer> matchOffersByUserTags(Integer userid) {
        User user = userService.getUser(userid);
        List<Tag> tags = user.getTags();
        return this.offerRepository.findByTagsIn(tags);
    }

}