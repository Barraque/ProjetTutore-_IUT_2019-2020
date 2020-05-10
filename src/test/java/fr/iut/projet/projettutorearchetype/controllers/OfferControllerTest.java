package fr.iut.projet.projettutorearchetype.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.iut.projet.projettutorearchetype.constants.Constants;
import fr.iut.projet.projettutorearchetype.controller.OfferController;
import fr.iut.projet.projettutorearchetype.jwt.AuthEntryPointJwt;
import fr.iut.projet.projettutorearchetype.models.Offer;
import fr.iut.projet.projettutorearchetype.services.OfferService;
import fr.iut.projet.projettutorearchetype.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@EnableWebMvc
@ActiveProfiles(profiles = "test")
@WebMvcTest(OfferController.class)
public class OfferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AuthEntryPointJwt authEntryPointJwt;

    @MockBean
    private OfferService offerService;

    @MockBean
    UserService userService;

    Offer goodOffer;
    List<Offer> offerList;

    @Before
    public void setUp(){

        goodOffer = new Offer();
        goodOffer.setOfferId(1);
        goodOffer.setTitle("OfferTitleTest");

        offerList = new ArrayList<>();
        offerList.add(goodOffer);

        //when get good id return good id
        //if 404 then throw
        when(offerService.addOffer(any())).thenReturn(goodOffer);
        when(offerService.getOffer(1)).thenReturn(goodOffer);
        when(offerService.getOffer(2)).thenReturn(null);
        when(offerService.getAllOffers()).thenReturn(offerList);

        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void AddValidOffer() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        String bodyJson = ow.writeValueAsString(goodOffer);
        String url = "/offer";
        String contentTypeUTF = "application/json;charset=UTF-8";

        MvcResult result = mockMvc.perform(post(url)
                .contentType(contentTypeUTF)
                .content(bodyJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();


        String bodyContent = result.getResponse().getContentAsString();
        System.out.println("Body Content === "+ bodyContent);
    }


    //DOESNT WORK
    @Test
    public void AddNotValidOffer() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        Offer badOffer = new Offer();
        String bodyJson = ow.writeValueAsString(badOffer);
        String url = "/offer";
        String contentTypeUTF = "application/json;charset=UTF-8";

        MvcResult result = mockMvc.perform(post(url)
                .contentType(contentTypeUTF)
                .content(bodyJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();


        String bodyContent = result.getResponse().getContentAsString();
        System.out.println("Body Content === "+ bodyContent);
    }


    @Test
    public void getOffer() throws Exception {
        MvcResult result = mockMvc.perform(get("/offer/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String bodyContent = result.getResponse().getContentAsString();
        assertEquals(bodyContent,"{\"offerId\":1,\"title\":\"OfferTitleTest\",\"tags\":null,\"offerFile\":null}");
    }

    @Test
    public void getUnknownOffer() throws Exception {
        MvcResult result = mockMvc.perform(get("/offer/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String bodyContent = result.getResponse().getContentAsString();
        assertEquals(bodyContent,"");
    }

    @Test
    public void getAllOffers() throws Exception {
        mockMvc.perform(get("/offer/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}