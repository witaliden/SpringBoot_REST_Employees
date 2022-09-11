package wd.EmployeesREST.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get employees by firstname and lastname. Don't fill parameters if you want to get all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got employees",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "400", description = "invalid parameters supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "employee with ID not found", content = @Content)})
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