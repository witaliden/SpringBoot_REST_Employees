package com.EmployeesREST;

import com.EmployeesREST.dao.EmployeeRepository;
import com.EmployeesREST.dto.Employee;
import com.EmployeesREST.dto.Gender;
import com.EmployeesREST.rest.EmployeeController;
import com.EmployeesREST.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {
    @InjectMocks
    private EmployeeController employeeController;
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void testAddEmployee() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Employee employee = new Employee("John", "Konor",
                new Random().nextInt(1,9), "Intern", Gender.MALE, LocalDate.of(2000, 2, 2));

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee employeeToRequest = new Employee("John", "Konor",
                new Random().nextInt(1,9), "Intern", Gender.MALE, LocalDate.of(2000, 2, 2));

        /*ResponseEntity<String> responseEntity = employeeController.addEmployee(employeeToRequest);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);*/
    }

    @Test
    public void testFindAll() {
        Employee employee1 = new Employee("John", "Konor",
                new Random().nextInt(1,9), "Intern", Gender.MALE, LocalDate.of(2000, 2, 2));
        Employee employee2 = new Employee("John", "Konor",
                new Random().nextInt(1,9), "Intern", Gender.MALE, LocalDate.of(2000, 2, 2));

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getEmployeesByLastAndFirstName("", "");

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getFirstName()).isEqualTo(employee1.getFirstName());
        assertThat(result.get(1).getFirstName()).isEqualTo(employee2.getFirstName());
}
    }
