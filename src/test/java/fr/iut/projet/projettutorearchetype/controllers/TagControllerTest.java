package fr.iut.projet.projettutorearchetype.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.iut.projet.projettutorearchetype.constants.Constants;
import fr.iut.projet.projettutorearchetype.controller.TagController;
import fr.iut.projet.projettutorearchetype.models.Tag;
import fr.iut.projet.projettutorearchetype.services.TagService;
import fr.iut.projet.projettutorearchetype.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
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
@WebMvcTest(TagController.class)
@ActiveProfiles(profiles = "test")
public class TagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagService tagService;

    @MockBean
    UserService userService;

    Tag goodTag;
    List<Tag> tagList;

    @Before
    public void setUp(){

        goodTag = new Tag();
        goodTag.setTagId(1);
        goodTag.setName("TagNameTest");

        tagList = new ArrayList<>();
        tagList.add(goodTag);

        //when get good id return good id
        //if 404 then throw
        when(tagService.addTag(any())).thenReturn(goodTag);
        when(tagService.getTag(1)).thenReturn(goodTag);
        when(tagService.getTag(2)).thenReturn(null);
        when(tagService.getAllTags()).thenReturn(tagList);

        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void AddValidTag() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        String bodyJson = ow.writeValueAsString(goodTag);
        String url = "/tag";
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
    public void AddNotValidTag() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        Tag badTag = new Tag();
        String bodyJson = ow.writeValueAsString(badTag);
        String url = "/tag";
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
    public void getTag() throws Exception {
        MvcResult result = mockMvc.perform(get("/tag/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String bodyContent = result.getResponse().getContentAsString();
        assertEquals(bodyContent,"{\"tagId\":1,\"name\":\"TagNameTest\"}");
    }

    @Test
    public void getUnknownTag() throws Exception {
        MvcResult result = mockMvc.perform(get("/tag/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String bodyContent = result.getResponse().getContentAsString();
        assertEquals(bodyContent,"");
    }

    @Test
    public void getAllTags() throws Exception {
        mockMvc.perform(get("/tag/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}