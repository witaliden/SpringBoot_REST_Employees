package com.employees.rest

import com.employees.dto.Employee
import com.employees.service.EmployeeService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import mu.KotlinLogging
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/jms")
class JmsController(private val employeeService: EmployeeService) {
    @Operation(summary = "Add employee using JMS.")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "User was added."
        ), ApiResponse(
            responseCode = "400",
            description = "Invalid user data supplied."
        ), ApiResponse(responseCode = "500", description = "Internal server error.")]
    )
    @PostMapping
    fun addEmployeeUsingJMS(@RequestBody employee: @Valid Employee?) {
        log.info("IN: addEmployeeUsingJMS method in JmsController with data: {}", employee)
        employeeService.addEmployeeUsingJMS(employee)
        log.info("OUT: addEmployeeUsingJMS method in JmsController. New employee added: {}", employee)
    }
}