package wd.EmployeesREST.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import wd.EmployeesREST.Exceptions.ResourceNotFoundException;
import wd.EmployeesREST.dao.EmployeeRepository;
import wd.EmployeesREST.dto.Employee;

@Slf4j
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(long employeeID) throws ResourceNotFoundException {
        log.info("Starting getEmployeeById with ID {}", employeeID);
        Employee returnedEmployee = employeeRepository.findById(employeeID).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Employee with id %d does not exist!", employeeID)));
        log.info("Exiting getEmployeeById with result: {}", returnedEmployee);
        return returnedEmployee;
    }

    public List<Employee> getEmployeesByLastAndFirstName(String lastName, String firstName) throws ResourceNotFoundException {
        log.info("Starting getEmployeesByLastAndFirstName with firstname - {} and lastname - {}", firstName, lastName);
        List<Employee> employeeSearchResult = employeeRepository.findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(lastName, firstName).stream().toList();
        if (employeeSearchResult.isEmpty()) {
            throw new ResourceNotFoundException(String.format("There is no employee with lastname %s and firstname %s", lastName, firstName));
        }
        log.info("Exiting getEmployeesByLastAndFirstName with result: {}", employeeSearchResult);
        return employeeSearchResult;
    }

    public void addEmployee(Employee newEmployee) throws IllegalArgumentException {
        log.info("Starting addEmployee with input object {}", newEmployee);
        employeeRepository.save(newEmployee);
    }

    public void deleteEmployee(Long employeeToDeleteId) throws ResourceNotFoundException {
        log.info("Starting deleteEmployee with id {}", employeeToDeleteId);
        employeeRepository.deleteById(employeeToDeleteId);
    }

    @Transactional
    public void update(Employee updatedEmployee, long employeeId) throws ResourceNotFoundException {
        log.info("Starting updatedEmployee with id {} and data {}", employeeId, updatedEmployee);
        employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Employee with id %d does not exist!", employeeId)));

        employeeRepository.save(Employee.builder().employeeID(employeeId).firstName(updatedEmployee.getFirstName())
                .lastName(updatedEmployee.getLastName()).dateOfBirth(updatedEmployee.getDateOfBirth()).gender(updatedEmployee
                        .getGender()).departmentID(updatedEmployee.getDepartmentID()).jobTitle(updatedEmployee.getJobTitle()).build());
    }
}
