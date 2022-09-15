package com.EmployeesREST;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

/*@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {
    @InjectMocks
    private EmployeeController employeeController;
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepository;
    Faker faker = new Faker();*/

/*    @Test
    public void testAddEmployee() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Employee employee = new Employee(faker.name().firstName(), faker.name().lastName(),
                faker.number().numberBetween(1, 9), faker.job().title(), Gender.values()[faker.number().numberBetween(0,1)], faker.date().past(6580, TimeUnit.DAYS));

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee employeeToRequest = new Employee(faker.name().firstName(), faker.name().lastName(),
                faker.number().numberBetween(1, 9), faker.job().title(), Gender.values()[faker.number().numberBetween(0,1)], faker.date().past(6580, TimeUnit.DAYS));

        ResponseEntity<String> responseEntity = employeeController.addEmployee(employeeToRequest);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void testFindAll() {
        Employee employee1 = new Employee(faker.name().firstName(), faker.name().lastName(),
                faker.number().numberBetween(1, 9), faker.job().title(), Gender.values()[faker.number().numberBetween(0,1)], new LocalDate.MIN(LocalDate.of(1950,1,1)),faker.date().past(6580, TimeUnit.DAYS));
        Employee employee2 = new Employee(faker.name().firstName(), faker.name().lastName(),
                faker.number().numberBetween(1, 9), faker.job().title(), Gender.values()[faker.number().numberBetween(0,1)], faker.date().past(6580, TimeUnit.DAYS));

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getEmployeesByFirstAndLastName("", "");

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getFirstName()).isEqualTo(employee1.getFirstName());
        assertThat(result.get(1).getFirstName()).isEqualTo(employee2.getFirstName());
}
    }*/
