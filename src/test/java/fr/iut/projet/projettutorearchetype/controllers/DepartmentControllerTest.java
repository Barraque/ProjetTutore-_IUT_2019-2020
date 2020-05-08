package fr.iut.projet.projettutorearchetype.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.iut.projet.projettutorearchetype.constants.Constants;
import fr.iut.projet.projettutorearchetype.controller.DepartmentController;
import fr.iut.projet.projettutorearchetype.models.Department;
import fr.iut.projet.projettutorearchetype.services.DepartmentService;
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
@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @MockBean
    UserService userService;

    Department goodDepartment;
    List<Department> departmentList;

    @Before
    public void setUp(){

        goodDepartment = new Department();
        goodDepartment.setDepartmentId(1);
        goodDepartment.setName("DepartmentNameTest");

        departmentList = new ArrayList<>();
        departmentList.add(goodDepartment);

        //when get good id return good id
        //if 404 then throw
        when(departmentService.addDepartment(any())).thenReturn(goodDepartment);
        when(departmentService.getDepartment(1)).thenReturn(goodDepartment);
        when(departmentService.getDepartment(2)).thenReturn(null);
        when(departmentService.getAllDepartments()).thenReturn(departmentList);

        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void AddValidDepartment() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        String bodyJson = ow.writeValueAsString(goodDepartment);
        String url = "/" + Constants.apiConstant + "department";
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
    public void AddNotValidDepartment() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        Department badDepartment = new Department();
        String bodyJson = ow.writeValueAsString(badDepartment);
        String url = "/" + Constants.apiConstant + "department";
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
    public void getDepartment() throws Exception {
        MvcResult result = mockMvc.perform(get("/"+Constants.apiConstant+"/department/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String bodyContent = result.getResponse().getContentAsString();
        assertEquals(bodyContent,"{\"departmentId\":1,\"name\":\"DepartmentNameTest\"}");
    }

    @Test
    public void getUnknownDepartment() throws Exception {
        MvcResult result = mockMvc.perform(get("/"+Constants.apiConstant+"/department/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String bodyContent = result.getResponse().getContentAsString();
        assertEquals(bodyContent,"");
    }

    @Test
    public void getAllDepartments() throws Exception {
        mockMvc.perform(get("/"+Constants.apiConstant+"/department/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}