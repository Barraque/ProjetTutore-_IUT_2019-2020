package fr.iut.projet.projettutorearchetype.repositories;

import fr.iut.projet.projettutorearchetype.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITagRepository extends JpaRepository<Tag, UUID> {
}
