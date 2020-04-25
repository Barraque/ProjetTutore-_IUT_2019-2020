package fr.iut.projet.projettutorearchetype.services;

import fr.iut.projet.projettutorearchetype.models.Tag;
import fr.iut.projet.projettutorearchetype.repositories.TagRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class TagServiceTest {

    @MockBean
    private TagRepository tagRepository;

    @InjectMocks
    private TagService tagService = new TagService();

    Tag goodTag;
    List<Tag> tagList;

    @Before
    public void setUp() throws Exception {

        goodTag = new Tag();
        goodTag.setTagId(1);
        goodTag.setName("TagNameTest");

        tagList = new ArrayList<>();
        tagList.add(goodTag);

        when(tagRepository.save(any())).thenReturn(goodTag);
        when(tagRepository.findById(1)).thenReturn(Optional.of(goodTag));
        when(tagRepository.findById(2)).thenReturn(Optional.empty());
        when(tagRepository.findAll()).thenReturn(tagList);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void AddValidTag()
    {
        Tag tagWeWantToAdd = new Tag();
        tagWeWantToAdd.setName("TagNameTest");

        Tag tagWeAdd = tagService.addTag(tagWeWantToAdd);

        assertEquals(tagWeAdd.getName(), goodTag.getName());
    }

    @Test
    public void AddNotValidTag()
    {
        Tag wrongTag = new Tag();
        //wrongTag's name has not been assigned

        Tag tagWeAdd = tagService.addTag(wrongTag);

        assertNotEquals(tagWeAdd.getName(), wrongTag.getName());
    }

    @Test
    public void getTag()
    {
        Tag test = tagService.getTag(1);
        assertEquals(test,goodTag);
    }

    @Test(expected = NoSuchElementException.class)
    public void getUnknownTag(){
        Tag notRealTag = tagService.getTag(2);
        assertNotEquals(notRealTag,goodTag);
    }

    @Test
    public void getAllTags(){
        List<Tag> tags = tagService.getAllTags();
        assertEquals(tags,tagList);
    }


}