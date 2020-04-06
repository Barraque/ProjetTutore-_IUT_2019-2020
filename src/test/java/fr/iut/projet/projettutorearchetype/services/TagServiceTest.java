package fr.iut.projet.projettutorearchetype.services;

import fr.iut.projet.projettutorearchetype.repositories.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.MockUtil;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
class TagServiceTest {

    @Mock
    TagRepository tagRepository;

    @InjectMocks
    TagService tagService;

/*
    @BeforeEach
    void setUp() {
    Mockito.when(tagRepository.save(Mockito.any()));
        Mockito.mock(tagRepository.saveAll(Mockito.any()));

    }

 */
}