package com.EmployeesREST.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

import com.EmployeesREST.dto.Employee;
import com.EmployeesREST.service.EmployeeService;

@Slf4j
@Validated
@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @Tag(name = "EmployeeController's get operations")
    @Operation(summary = "Get employees by firstname and lastname. Don't fill parameters if you want to get all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got employees",
                    content = @Content(schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public List<Employee> getEmployeesByLastAndFirstName(@RequestParam (defaultValue = "") String lastName,
                                                         @RequestParam (defaultValue = "") String firstName) {
        log.info("IN: starting getEmployeesByLastAndFirstName method in EmployeeController with lastname [{}] and firstname [{}]", lastName, firstName);
        List<Employee> employeesByLastAndFirstName = employeeService.getEmployeesByLastAndFirstName(lastName, firstName);
        log.info("OUT: Exiting getEmployeesByLastAndFirstName in EmployeeController with returned list size: {}", employeesByLastAndFirstName.size());
        return employeesByLastAndFirstName;
    }

    @GetMapping("/{id}")
    @Tag(name = "EmployeeController's get operations")
    @Operation(summary = "Get employee by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee was found.", content = {@Content(schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameter supplied"),
            @ApiResponse(responseCode = "404", description = "Method not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Employee getEmployeeById(@PathVariable(value = "id")
                                        @Parameter(description = "Enter employee's ID in the URL.") @Min(value = 1, message = "User ID should be greater than 0") long employeeID) {
        log.info("IN: Starting getEmployeeById method in EmployeeController, search by ID {}", employeeID);
        Employee employeeResult = employeeService.getEmployeeById(employeeID);
        log.info("OUT: Exiting getEmployeeById in EmployeeController with result: {}", employeeResult);
        return employeeResult;
    }

    @PostMapping()
    @Operation(summary = "Add employee.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was added."),
            @ApiResponse(responseCode = "400", description = "Invalid user data supplied."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    public void addEmployee(@Valid @RequestBody Employee newEmployee) {
        log.info("IN: Starting addEmployee method in EmployeeController with data: {}", newEmployee);
        employeeService.addEmployee(newEmployee);
        log.info("OUT: Exiting addEmployee method in EmployeeController. New employee added: {}", newEmployee);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update employee.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was updated."),
            @ApiResponse(responseCode = "400", description = "Invalid user data supplied."),
            @ApiResponse(responseCode = "404", description = "Object not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")})
    public void updateEmployee(@Valid @RequestBody Employee updatedEmployee,
                               @PathVariable(value = "id") @Min(value = 1, message = "User ID should be greater than 0") Long employeeID) {
        log.info("IN: Starting updateEmployee method in EmployeeController with ID {} and input data: {}", employeeID, updatedEmployee);
        employeeService.updateEmployee(updatedEmployee, employeeID);
        log.info("OUT: Exiting updateEmployee method in EmployeeController. Employee with ID {} updated: {}", employeeID, updatedEmployee);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete employee with ID as a parameter.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was deleted.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid user ID.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error.")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable(value = "id") @Min(value =1, message = "User ID should be greater than 0") Long employeeID) {
        log.info("IN: Starting deleteEmployee method in EmployeeController with id {}", employeeID);
        employeeService.deleteEmployee(employeeID);
        log.info("OUT: Exiting deleteEmployee method in EmployeeController. Employee {} deleted", employeeID);
    }
}