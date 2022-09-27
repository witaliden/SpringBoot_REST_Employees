package com.EmployeesREST.service;

import com.EmployeesREST.dto.Employee;
import com.EmployeesREST.jms.Producer;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import com.EmployeesREST.exceptions.EmployeeServiceNotFoundException;
import com.EmployeesREST.dao.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final Producer producer;

    public EmployeeService(EmployeeRepository employeeRepository, Producer producer) {
        this.employeeRepository = employeeRepository;
        this.producer = producer;
    }

    public Employee getEmployeeById(long employeeID) throws EmployeeServiceNotFoundException {
        return employeeRepository.findById(employeeID).orElseThrow(() ->
                new EmployeeServiceNotFoundException(String.format("Employee with id %d does not exist!", employeeID)));
    }

    public List<Employee> getEmployeesByLastAndFirstName(String lastName, String firstName) throws EmployeeServiceNotFoundException {
        return employeeRepository.findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(lastName, firstName);
    }

    public void addEmployee(Employee newEmployee) {
        employeeRepository.save(newEmployee);
    }

    public void deleteEmployee(Long employeeToDeleteId) throws EmployeeServiceNotFoundException {
        employeeRepository.deleteEmployeesByEmployeeID(employeeToDeleteId);
    }

    @Transactional
    public void updateEmployee(Employee updatedEmployee, long employeeId) throws EmployeeServiceNotFoundException {
        employeeRepository.findById(employeeId).orElseThrow(() ->
                new EmployeeServiceNotFoundException(String.format("Employee with id %d does not exist!", employeeId)));

        employeeRepository.save(Employee.builder().employeeID(employeeId).firstName(updatedEmployee.getFirstName())
                .lastName(updatedEmployee.getLastName()).dateOfBirth(updatedEmployee.getDateOfBirth()).gender(updatedEmployee
                        .getGender()).departmentID(updatedEmployee.getDepartmentID()).jobTitle(updatedEmployee.getJobTitle()).build());
    }

    public void addEmployeeUsingJMS(Employee employee){
        producer.addEmployee(employee);
    }
}
