package fr.iut.projet.projettutorearchetype.services;

import fr.iut.projet.projettutorearchetype.models.Department;
import fr.iut.projet.projettutorearchetype.models.RolesEnum;
import fr.iut.projet.projettutorearchetype.models.User;
import fr.iut.projet.projettutorearchetype.repositories.UserRepository;
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
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService = new UserService();

    User goodUser;
    List<User> userList;


    Department goodDepartment;

    @Before
    public void setUp() throws Exception {

        goodUser = new User();
        goodUser.setUserId(1);
        goodUser.setLogin("UserLoginTest");
        goodUser.setPassword("UserPasswordTest");
        goodUser.setFirstname("UserFirstnameTest");
        goodUser.setLastname("UserLastnameTest");
        goodUser.setMail("UserMailTest");
        goodUser.setRole(RolesEnum.STUDENT);
        goodUser.setFirstConnexion(1);

        goodDepartment = new Department();
        goodDepartment.setDepartmentId(1);
        goodDepartment.setName("DepartmentNameTest");

        goodUser.setDepartmentNumber(goodDepartment);




        userList = new ArrayList<>();
        userList.add(goodUser);

        when(userRepository.save(any())).thenReturn(goodUser);
        when(userRepository.findById(1)).thenReturn(Optional.of(goodUser));
        when(userRepository.findById(2)).thenReturn(Optional.empty());
        when(userRepository.findAll()).thenReturn(userList);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void AddValidUser()
    {
        User userToBeAdd = new User();
        userToBeAdd.setUserId(1);
        userToBeAdd.setLogin("UserLoginTest");
        userToBeAdd.setPassword("UserPasswordTest");
        userToBeAdd.setFirstname("UserFirstnameTest");
        userToBeAdd.setLastname("UserLastnameTest");
        userToBeAdd.setMail("UserMailTest");
        userToBeAdd.setRole(RolesEnum.STUDENT);
        userToBeAdd.setFirstConnexion(1);

        goodDepartment = new Department();
        goodDepartment.setDepartmentId(1);
        goodDepartment.setName("DepartmentNameTest");

        userToBeAdd.setDepartmentNumber(goodDepartment);

        User UserAdded = userService.addUser(userToBeAdd);

        //------assertEquals(userToBeAdd.getTitle(), UserAdded.getTitle());
    }

    @Test
    public void AddNotValidUser()
    {
        User wrongUser = new User();
        //wrongUser's name has not been assigned

        User userAdded = userService.addUser(wrongUser);

       //---- assertNotEquals(userAdded.getTitle(), wrongUser.getTitle());
    }

    @Test
    public void getUser()
    {
        User test = userService.getUser(1);
        assertEquals(test, goodUser);
    }

    @Test(expected = NoSuchElementException.class)
    public void getUnknownUser(){
        User notRealUser = userService.getUser(2);

        assertNotEquals(notRealUser,goodUser);
    }
}