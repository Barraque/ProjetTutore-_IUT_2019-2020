package fr.iut.projet.projettutorearchetype.services;

import fr.iut.projet.projettutorearchetype.models.Tag;
import fr.iut.projet.projettutorearchetype.repositories.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService {

    @Autowired
    ITagRepository tagRepository;

    public Tag addTag(final Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag getTag(final int tagId) {
        Optional<Tag> tag = tagRepository.findById(tagId);

        return tag.get();
    }

}
