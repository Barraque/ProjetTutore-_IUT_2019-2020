package fr.iut.projet.projettutorearchetype.services;

import fr.iut.projet.projettutorearchetype.models.Department;
import fr.iut.projet.projettutorearchetype.models.Offer;
import fr.iut.projet.projettutorearchetype.repositories.DepartmentRepository;
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
public class DepartmentServiceTest {

    @MockBean
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService = new DepartmentService();

    Department goodDepartment;
    List<Department> departmentList;

    @Before
    public void setUp() throws Exception {

        goodDepartment = new Department();
        goodDepartment.setDepartmentId(1);
        goodDepartment.setName("DepartmentNameTest");

        departmentList = new ArrayList<>();
        departmentList.add(goodDepartment);

        when(departmentRepository.save(any())).thenReturn(goodDepartment);
        when(departmentRepository.findById(1)).thenReturn(Optional.of(goodDepartment));
        when(departmentRepository.findById(2)).thenReturn(Optional.empty());
        when(departmentRepository.findAll()).thenReturn(departmentList);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void AddValidDepartment()
    {
        Department departmentToBeAdd = new Department();
        departmentToBeAdd.setName("DepartmentNameTest");

        Department DepartmentAdded = departmentService.addDepartment(departmentToBeAdd);

        assertEquals(departmentToBeAdd.getName(), DepartmentAdded.getName());
    }

    @Test
    public void AddNotValidDepartment()
    {
        Department wrongDepartment = new Department();
        //wrongDepartment's name has not been assigned

        Department departmentAdded = departmentService.addDepartment(wrongDepartment);

        assertNotEquals(departmentAdded.getName(), wrongDepartment.getName());
    }

    @Test
    public void getDepartment()
    {
        Department test = departmentService.getDepartment(1);
        assertEquals(test, goodDepartment);
    }

    @Test(expected = NoSuchElementException.class)
    public void getUnknownDepartment(){
        Department notRealDepartment = departmentService.getDepartment(2);

        assertNotEquals(notRealDepartment,goodDepartment);
    }

    @Test
    public void getAllDepartments(){
        List<Department> allDepartments = departmentService.getAllDepartments();
        assertEquals(allDepartments,departmentList);
    }
}