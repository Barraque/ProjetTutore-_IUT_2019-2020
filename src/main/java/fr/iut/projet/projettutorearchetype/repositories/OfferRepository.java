package fr.iut.projet.projettutorearchetype.repositories;

import fr.iut.projet.projettutorearchetype.models.Offer;
import fr.iut.projet.projettutorearchetype.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer> {

    List<Offer> findByTagsIn(Collection<Tag> tags);

}
