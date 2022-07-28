package wd.EmployeesREST.rest;

import org.springframework.beans.factory.annotation.Autowired;
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
    public final EmployeeService employeeeService;
    EmployeeController(EmployeeService employeeeService) {
        this.employeeeService = employeeeService;
    }
    @GetMapping(path = "/all")
    ResponseEntity<List<Employee>> getAll(){
        return new ResponseEntity<>(employeeeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable(value = "id") long employee_id) {
        return new ResponseEntity<>(employeeeService.getById(employee_id), HttpStatus.OK);
    }

    @GetMapping("/search/ln")
    public ResponseEntity<List<Employee>> getByLastName(@RequestParam String lName) {
        return new ResponseEntity<>(employeeeService.getByLastname(lName).stream().toList(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody Employee newEmployee){
        employeeeService.add(newEmployee);
        return new ResponseEntity<>("User was successfully created", HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee updatedEmployee, @PathVariable String id){
        employeeeService.update(updatedEmployee);
        return new ResponseEntity<>("Employee was updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(value = "id") Long employeeToDeleteId) {
        try {
            employeeeService.delete(employeeToDeleteId);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException("There is no employee with id " + employeeToDeleteId);
        }
        return new ResponseEntity<>("Employee was successfully deleted", HttpStatus.OK);
    }
}