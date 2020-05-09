package fr.iut.projet.projettutorearchetype.services;

import fr.iut.projet.projettutorearchetype.models.CV;
import fr.iut.projet.projettutorearchetype.models.Tag;
import fr.iut.projet.projettutorearchetype.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    public Tag addTag(final Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag getTag(final int tagId){
        Optional<Tag> tag = tagRepository.findById(tagId);

        if (tag.isEmpty()) {
            throw new NoSuchElementException("Unknown tag with ID [" + tagId + "]");
        }

        return tag.get();
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

}
