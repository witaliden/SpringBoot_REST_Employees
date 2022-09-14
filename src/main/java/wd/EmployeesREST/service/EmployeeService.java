package wd.EmployeesREST.service;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import wd.EmployeesREST.exceptions.EmployeeServiceNotFoundException;
import wd.EmployeesREST.dao.EmployeeRepository;
import wd.EmployeesREST.dto.Employee;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(long employeeID) throws EmployeeServiceNotFoundException {
        Employee returnedEmployee = employeeRepository.findById(employeeID).orElseThrow(() ->
                new EmployeeServiceNotFoundException(String.format("Employee with id %d does not exist!", employeeID)));
        return returnedEmployee;
    }

    public List<Employee> getEmployeesByLastAndFirstName(String lastName, String firstName) throws EmployeeServiceNotFoundException {
        List<Employee> employeeSearchResult = employeeRepository.findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(lastName, firstName).stream().toList();
        if (employeeSearchResult.isEmpty()) {
            throw new EmployeeServiceNotFoundException(String.format("There is no employee with lastname %s and firstname %s", lastName, firstName));
        }
        return employeeSearchResult;
    }

    public void addEmployee(Employee newEmployee) {
        employeeRepository.save(newEmployee);
    }

    public void deleteEmployee(Long employeeToDeleteId) throws EmployeeServiceNotFoundException {
        employeeRepository.deleteById(employeeToDeleteId);
    }

    @Transactional
    public void update(Employee updatedEmployee, long employeeId) throws EmployeeServiceNotFoundException {
        employeeRepository.findById(employeeId).orElseThrow(() ->
                new EmployeeServiceNotFoundException(String.format("Employee with id %d does not exist!", employeeId)));

        employeeRepository.save(Employee.builder().employeeID(employeeId).firstName(updatedEmployee.getFirstName())
                .lastName(updatedEmployee.getLastName()).dateOfBirth(updatedEmployee.getDateOfBirth()).gender(updatedEmployee
                        .getGender()).departmentID(updatedEmployee.getDepartmentID()).jobTitle(updatedEmployee.getJobTitle()).build());
    }
}
