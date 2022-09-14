package wd.EmployeesREST.rest;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

import wd.EmployeesREST.dto.Employee;
import wd.EmployeesREST.service.EmployeeService;

@Slf4j
@Validated
@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Hidden
    @GetMapping("/log")
    public String log() {
        log.trace("This is a TRACE level message");
        log.debug("This is a DEBUG level message");
        log.info("This is an INFO level message");
        log.warn("This is a WARN level message");
        log.error("This is an ERROR level message");
        return "See the log for details";
    }

    @Operation(summary = "Get employees by firstname and lastname. Don't fill parameters if you want to get all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got employees",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "400", description = "invalid parameters supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Method not found", content = @Content)})
    @GetMapping
    public List<Employee> getEmployeesByLastAndFirstName(@RequestParam String lastName, String firstName) {
        log.info("Starting getEmployeesByLastAndFirstName in EmployeeController with lastname [{}] and firstname [{}]", lastName, firstName);
        List<Employee> employeesByLastAndFirstName = employeeService.getEmployeesByLastAndFirstName(lastName, firstName);
        log.info("Exiting getEmployeesByLastAndFirstName in EmployeeController with returned list size: {}", employeesByLastAndFirstName.size());
        return employeesByLastAndFirstName;
    }

    @Operation(summary = "Get employee by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee was found.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameter  supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Method not found", content = @Content)})
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") @Parameter(description = "Put employee's ID in the URL.") @Min((1)) long employeeID) {
        log.info("Starting getEmployeeById in EmployeeController, search by ID {}", employeeID);
        Employee employeeResult = employeeService.getEmployeeById(employeeID);
        log.info("Exiting getEmployeeById in EmployeeController with result: {}", employeeResult);
        return employeeResult;
    }

    @Operation(summary = "Add employee.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was added.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid user data supplied.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Method not found.", content = @Content)})
    @PostMapping()
    public void addEmployee(@Valid @RequestBody Employee newEmployee) {
        log.info("Starting addEmployee in EmployeeController with data: {}", newEmployee);
        employeeService.addEmployee(newEmployee);
        log.info("Exiting addEmployee method in EmployeeController.");
    }

    @Operation(summary = "Update employee.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was updated.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid user data supplied.", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found.", content = @Content)})
    @PutMapping("/{id}")
    public void updateEmployee(@Valid @RequestBody Employee updatedEmployee, @PathVariable(value = "id") @Min(1) Long employeeID) {
        log.info("Starting updateEmployee in EmployeeController with ID {} and input data: {}", employeeID, updatedEmployee);
        employeeService.update(updatedEmployee, employeeID);
        log.info("Exiting updateEmployee method in EmployeeController.");
    }
    @Operation(summary = "Delete employee with ID as a parameter.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was deleted.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid user ID.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Method not found.", content = @Content)})
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable(value = "id") @Min(1) Long employeeID) {
        log.info("Starting deleteEmployee in EmployeeController with id {}", employeeID);
        employeeService.deleteEmployee(employeeID);
        log.info("Exiting deleteEmployee method in EmployeeController.");
    }
}