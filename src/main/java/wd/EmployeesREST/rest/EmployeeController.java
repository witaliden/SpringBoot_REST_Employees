package wd.EmployeesREST.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wd.EmployeesREST.Exceptions.ResourceNotFoundException;
import wd.EmployeesREST.dao.EmployeeRepository;
import wd.EmployeesREST.dto.Employee;
import wd.EmployeesREST.service.EmployeeeService;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    public final EmployeeeService employeeeService;
    EmployeeController(EmployeeeService employeeeService) {
        this.employeeeService = employeeeService;
    }
    @GetMapping(path = "/all")
        public List<Employee> getAll() {
            return employeeeService.getAll();
        }
/*      public CollectionModel<EntityModel<Employee>> getAllEmployees(){
        List<EntityModel<Employee>> employees = employeeRepository.findAll().stream()
                .map(employee -> {
                    try {
                        return EntityModel.of(employee,
                                linkTo(methodOn(EmployeeController.class).getEmployeeById(employee.getEmployee_id())).withSelfRel(),
                                linkTo(methodOn(EmployeeController.class).getAllEmployees()).withRel("employees"));
                    } catch (ResourceNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
                return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).getAllEmployees()).withSelfRel());
    }*/

    @GetMapping("/{id}")
    public Employee getById(@PathVariable(value = "id") long employee_id) {
            return employeeeService.getById(employee_id);
    }

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee newEmployee){
        employeeeService.add(newEmployee);
        return newEmployee;
    }

    @PutMapping("/edit/{id}")
    public void updateEmployee(@RequestBody Employee updatedEmployee){
        employeeeService.update(updatedEmployee);
    }

    @DeleteMapping("/delete/{employee_id}")
    public void deleteEmployee(@PathVariable(value = "employee_id") Long employeeToDeleteId) {
        try {
            employeeeService.delete(employeeToDeleteId);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException("There is no employee with id " + employeeToDeleteId);
        }
    }
}