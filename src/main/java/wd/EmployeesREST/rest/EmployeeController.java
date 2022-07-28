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
    @GetMapping(path = "/all")
    ResponseEntity<List<Employee>> getAll(){
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable(value = "id") long employee_id) {
        return new ResponseEntity<>(employeeService.getById(employee_id), HttpStatus.OK);
    }

    @GetMapping("/search/lastname-equals")
    public ResponseEntity<List<Employee>> getByLastName(@RequestParam String lName) {
        return new ResponseEntity<>(employeeService.getByLastname(lName).stream().toList(), HttpStatus.OK);
    }
    @GetMapping("/search/lastname-like")
    public ResponseEntity<List<Employee>> getByLastNameLike(@RequestParam String lName) {
        return new ResponseEntity<>(employeeService.getByLastnameContaining(lName).stream().toList(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody Employee newEmployee){
        employeeService.add(newEmployee);
        return new ResponseEntity<>("User was successfully created", HttpStatus.OK);
    }

    @PutMapping("/edit/")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee updatedEmployee){
        employeeService.update(updatedEmployee);
        return new ResponseEntity<>("Employee was updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(value = "id") Long employeeToDeleteId) {
        try {
            employeeService.delete(employeeToDeleteId);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException("There is no employee with id " + employeeToDeleteId);
        }
        return new ResponseEntity<>("Employee was successfully deleted", HttpStatus.OK);
    }
}