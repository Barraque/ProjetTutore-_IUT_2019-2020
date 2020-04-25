package fr.iut.projet.projettutorearchetype.services;

import fr.iut.projet.projettutorearchetype.models.CV;
import fr.iut.projet.projettutorearchetype.models.Department;
import fr.iut.projet.projettutorearchetype.models.RolesEnum;
import fr.iut.projet.projettutorearchetype.models.User;
import fr.iut.projet.projettutorearchetype.repositories.CVRepository;
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
public class CVServiceTest {
/*
    @MockBean
    private CVRepository cvRepository;

    @InjectMocks
    private CVService cvService = new CVService();

    CV goodCV;
    List<CV> cvList;

    @Before
    public void setUp() throws Exception {

        goodCV = new CV();
        goodCV.setCvId(1);

        User goodUser = new User();
        goodUser.setUserId(1);
        goodUser.setLogin("UserLoginTest");
        goodUser.setPassword("UserPasswordTest");
        goodUser.setFirstname("UserFirstnameTest");
        goodUser.setLastname("UserLastnameTest");
        goodUser.setMail("UserMailTest");
        goodUser.setRole(RolesEnum.STUDENT);
        goodUser.setFirstConnexion(1);

        Department goodDepartment = new Department();
        goodDepartment.setDepartmentId(1);
        goodDepartment.setName("DepartmentNameTest");

        goodUser.setDepartmentNumber(goodDepartment);

        goodCV.setUser(goodUser);

        goodCV.setStatus(0);


        List<Byte> cvFile = new ArrayList<>();
        Byte byt = 0;
        cvFile.add(byt);

        goodCV.setCvFile(cvFile);

        cvList = new ArrayList<>();
        cvList.add(goodCV);

        when(cvRepository.save(any())).thenReturn(goodCV);
        when(cvRepository.findById(1)).thenReturn(Optional.of(goodCV));
        when(cvRepository.findById(2)).thenReturn(Optional.empty());
        when(cvRepository.findAll()).thenReturn(cvList);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void AddValidCV()
    {
        CV cvWeWantToAdd = new CV();
        cvWeWantToAdd.setName("CVNameTest");

        CV cvWeAdd = cvService.addCV(cvWeWantToAdd);

        assertEquals(cvWeAdd.getName(), goodCV.getName());
    }

    @Test
    public void AddNotValidCV()
    {
        CV wrongCV = new CV();
        //wrongCV's name has not been assigned

        CV cvWeAdd = cvService.addCV(wrongCV);

        assertNotEquals(cvWeAdd.getName(), wrongCV.getName());
    }

    @Test
    public void getCV()
    {
        CV test = cvService.getCV(1);
        assertEquals(test,goodCV);
    }

    @Test(expected = NoSuchElementException.class)
    public void getUnknownCV(){
        CV notRealCV = cvService.getCV(2);
        assertNotEquals(notRealCV,goodCV);
    }

    @Test
    public void getCV(){
        List<CV> cvs = cvService.getAllCV();
        assertEquals(cvs,cvList);
    }


 */


}