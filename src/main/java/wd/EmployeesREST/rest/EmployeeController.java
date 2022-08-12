package wd.EmployeesREST.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wd.EmployeesREST.Exceptions.ResourceNotFoundException;
import wd.EmployeesREST.dto.Employee;
import wd.EmployeesREST.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {
    public final EmployeeService employeeService;
    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/")
    ResponseEntity<List<Employee>> getAll(){
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable(value = "id") long employee_id) {
        return new ResponseEntity<>(employeeService.getById(employee_id), HttpStatus.OK);
    }

    @GetMapping("/search/by-lastname")
    public ResponseEntity<List<Employee>> getByLastName(@RequestParam String lastName) {
        return new ResponseEntity<>(employeeService.getByLastname(lastName).stream().toList(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> addEmployee(@RequestBody Employee newEmployee){
        employeeService.add(newEmployee);
        return new ResponseEntity<>("User was successfully created", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee updatedEmployee, @PathVariable(value = "id") Long employeeID){
        employeeService.update(updatedEmployee, employeeID);
        return new ResponseEntity<>("Employee was updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        try {
            employeeService.delete(employeeId);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException("There is no employee with id " + employeeId);
        }
        return new ResponseEntity<>("Employee was successfully deleted", HttpStatus.OK);
    }
}