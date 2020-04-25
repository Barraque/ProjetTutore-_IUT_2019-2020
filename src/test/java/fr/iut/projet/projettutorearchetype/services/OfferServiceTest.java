package fr.iut.projet.projettutorearchetype.services;

import fr.iut.projet.projettutorearchetype.models.Offer;
import fr.iut.projet.projettutorearchetype.models.Tag;
import fr.iut.projet.projettutorearchetype.repositories.OfferRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class OfferServiceTest {

    @MockBean
    private OfferRepository offerRepository;

    @InjectMocks
    private OfferService offerService = new OfferService();

    Offer goodOffer;
    List<Offer> offerList;

    @Before
    public void setUp() throws Exception {

        goodOffer = new Offer();
        goodOffer.setOfferId(1);
        goodOffer.setTitle("OfferTitleTest");

        List<Byte> offerFile = new ArrayList<>();
        Byte byt = 0;
        offerFile.add(byt);

        goodOffer.setOfferFile(offerFile);

        offerList = new ArrayList<>();
        offerList.add(goodOffer);

        when(offerRepository.save(any())).thenReturn(goodOffer);
        when(offerRepository.findById(1)).thenReturn(Optional.of(goodOffer));
        when(offerRepository.findById(2)).thenReturn(Optional.empty());
        when(offerRepository.findAll()).thenReturn(offerList);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void AddValidOffer()
    {
        Offer offerToBeAdd = new Offer();
        offerToBeAdd.setTitle("OfferTitleTest");

        Offer OfferAdded = offerService.addOffer(offerToBeAdd);

        assertEquals(offerToBeAdd.getTitle(), OfferAdded.getTitle());
    }

    @Test
    public void AddNotValidOffer()
    {
        Offer wrongOffer = new Offer();
        //wrongOffer's name has not been assigned

        Offer offerAdded = offerService.addOffer(wrongOffer);

        assertNotEquals(offerAdded.getTitle(), wrongOffer.getTitle());
    }

    @Test
    public void getOffer()
    {
        Offer test = offerService.getOffer(1);
        assertEquals(test, goodOffer);
    }

    @Test(expected = NoSuchElementException.class)
    public void getUnknownOffer(){
        Offer notRealOffer = offerService.getOffer(2);
        assertNotEquals(notRealOffer,goodOffer);
    }

    @Test
    public void getAllOffers(){
        List<Offer> allOffers = offerService.getAllOffers();
        assertEquals(allOffers,offerList);
    }
}