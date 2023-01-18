package com.employees.rest

import com.employees.dto.Employee
import com.employees.service.EmployeeService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min

private val log = KotlinLogging.logger {}

@Validated
@RestController
@RequestMapping("/v1/employees")
open class EmployeeController(private val employeeService: EmployeeService) {
    @GetMapping
    @Tag(name = "EmployeeController's get operations")
    @Operation(summary = "Get employees by firstname and lastname. Don't fill parameters if you want to get all employees")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Got employees",
            content = arrayOf(Content(schema = Schema(implementation = Employee::class)))
        ), ApiResponse(responseCode = "500", description = "Internal server error")]
    )
    fun getEmployeesByLastAndFirstName(
        @RequestParam(defaultValue = "") lastName: String?,
        @RequestParam(defaultValue = "") firstName: String?
    ): List<Employee?> {
        log.info(
            "IN: starting getEmployeesByLastAndFirstName method in EmployeeController with lastname [{}] and firstname [{}]",
            lastName,
            firstName
        )
        val employeesByLastAndFirstName: List<Employee?>? =
            this.employeeService.getEmployeesByLastAndFirstName(lastName, firstName)
        log.info(
            "OUT: Exiting getEmployeesByLastAndFirstName in EmployeeController with returned list size: {}",
            employeesByLastAndFirstName!!.size
        )
        return employeesByLastAndFirstName
    }

    @GetMapping("/{id}")
    @Tag(name = "EmployeeController's get operations")
    @Operation(summary = "Get employee by ID.")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Employee was found.",
            content = [Content(schema = Schema(implementation = Employee::class))]
        ), ApiResponse(
            responseCode = "400",
            description = "Invalid parameter supplied"
        ), ApiResponse(responseCode = "404", description = "Method not found"), ApiResponse(
            responseCode = "500",
            description = "Internal server error"
        )]
    )
    fun getEmployeeById(
        @PathVariable(value = "id") @Parameter(description = "Enter employee's ID in the URL.") employeeID: @Min(
            value = 1,
            message = "User ID should be greater than 0"
        ) Long
    ): Employee? {
        log.info("IN: Starting getEmployeeById method in EmployeeController, search by ID {}", employeeID)
        val employeeResult: Employee? = employeeService.getEmployeeById(employeeID)
        log.info("OUT: Exiting getEmployeeById in EmployeeController with result: {}", employeeResult)
        return employeeResult
    }

    @PostMapping
    @Operation(summary = "Add employee.")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "User was added."
        ), ApiResponse(
            responseCode = "400",
            description = "Invalid user data supplied."
        ), ApiResponse(responseCode = "500", description = "Internal server error.")]
    )
    fun addEmployee(@RequestBody newEmployee: @Valid Employee?) {
        log.info("IN: Starting addEmployee method in EmployeeController with data: {}", newEmployee)
        employeeService.addEmployee(newEmployee)
        log.info("OUT: Exiting addEmployee method in EmployeeController. New employee added: {}", newEmployee)
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update employee.")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "User was updated."), ApiResponse(
            responseCode = "400",
            description = "Invalid user data supplied."
        ), ApiResponse(responseCode = "404", description = "Object not found."), ApiResponse(
            responseCode = "500",
            description = "Internal server error."
        )]
    )
    fun updateEmployee(
        @RequestBody updatedEmployee: @Valid Employee?,
        @PathVariable(value = "id") employeeID: @Min(value = 1, message = "User ID should be greater than 0") Long?
    ) {
        log.info(
            "IN: Starting updateEmployee method in EmployeeController with ID {} and input data: {}",
            employeeID,
            updatedEmployee
        )
        updatedEmployee?.let { employeeService.updateEmployee(it, employeeID!!) }
        log.info(
            "OUT: Exiting updateEmployee method in EmployeeController. Employee with ID {} updated: {}",
            employeeID,
            updatedEmployee
        )
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete employee with ID as a parameter.")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "User was deleted."/*,
            content = Content*/
        ), ApiResponse(responseCode = "400", description = "Invalid user ID."/*, content = Content*/), ApiResponse(
            responseCode = "500",
            description = "Internal server error."
        )]
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteEmployee(
        @PathVariable(value = "id") employeeID: @Min(
            value = 1,
            message = "User ID should be greater than 0"
        ) Long?
    ) {
        log.info("IN: Starting deleteEmployee method in EmployeeController with id {}", employeeID)
        employeeService.deleteEmployee(employeeID)
        log.info("OUT: Exiting deleteEmployee method in EmployeeController. Employee {} deleted", employeeID)
    }
}