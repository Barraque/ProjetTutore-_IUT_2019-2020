package fr.iut.projet.projettutorearchetype.repositories;

import fr.iut.projet.projettutorearchetype.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer> {
}
