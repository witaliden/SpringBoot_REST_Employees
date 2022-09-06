package wd.EmployeesREST.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import wd.EmployeesREST.dto.Employee;
import wd.EmployeesREST.service.EmployeeService;

@Slf4j
@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployeesByLastAndFirstName(@RequestParam String lastName, String firstName) {
        log.info("Starting getEmployeesByLastAndFirstName with lastname - {} and firstname - {}", lastName, firstName);
        List<Employee> employeesByLastAndFirstName = employeeService.getEmployeesByLastAndFirstName(lastName, firstName);
        log.info("Exiting getEmployeesByLastAndFirstName in controller with result: {}", employeesByLastAndFirstName);
        return employeesByLastAndFirstName;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") long employeeID) {
        log.info("Starting getEmployeeById, search by ID {}", employeeID);
        Employee e = employeeService.getEmployeeById(employeeID);
        log.info("Exiting getEmployeeById in controller with result: {}", e);
        return e;
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public void addEmployee(@Valid @RequestBody Employee newEmployee) {
        log.info("Starting addEmployee in EmployeeController with data: {}", newEmployee);
        employeeService.addEmployee(newEmployee);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@Valid @RequestBody Employee updatedEmployee, @PathVariable(value = "id") Long employeeID) {
        log.info("Starting updateEmployee in EmployeeController with ID {} and input data: {}", employeeID, updatedEmployee);
        employeeService.update(updatedEmployee, employeeID);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable(value = "id") Long employeeID) {
        log.info("Starting deleteEmployee in EmployeeController with id {}", employeeID);
        employeeService.deleteEmployee(employeeID);
    }
}