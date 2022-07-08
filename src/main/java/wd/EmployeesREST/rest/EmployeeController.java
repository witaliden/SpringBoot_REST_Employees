package wd.EmployeesREST.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wd.EmployeesREST.Exceptions.ResourceNotFoundException;
import wd.EmployeesREST.dao.EmployeeRepository;
import wd.EmployeesREST.dto.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/all")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long employee_id)
    throws ResourceNotFoundException {
            Employee employee = employeeRepository.findById(employee_id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for id: " + employee_id));
            return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employee_id,
                                                   @RequestBody Employee employeeDet) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employee_id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for id: " + employee_id));
        employee.setFirst_name(employeeDet.getFirst_name());
        employee.setLast_name(employeeDet.getLast_name());
        //employee.setDepartment_id(employeeDet.getDepartment_id());
        //employee.setJob_title(employeeDet.getJob_title());
        employee.setGender(employeeDet.getGender());
        //employee.setDate_of_birth(employeeDet.getDate_of_birth());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{employee_id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "employee_id") Long employee_id) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employee_id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for id: " + employee_id));
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
