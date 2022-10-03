package com.EmployeesREST.rest;

import com.EmployeesREST.dto.Employee;
import com.EmployeesREST.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/jms")
public class JmsController {
    private final EmployeeService employeeService;
    public JmsController(EmployeeService employeeService, JmsTemplate jmsTemplate) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Add employee using JMS.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was added."),
            @ApiResponse(responseCode = "400", description = "Invalid user data supplied."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PostMapping
    public void addEmployeeUsingJMS(@Valid @RequestBody Employee employee) {
        log.info("IN: addEmployeeUsingJMS method in JmsController with data: {}", employee);
        employeeService.addEmployeeUsingJMS(employee);
        log.info("OUT: addEmployeeUsingJMS method in JmsController. New employee added: {}", employee);
    }

}
