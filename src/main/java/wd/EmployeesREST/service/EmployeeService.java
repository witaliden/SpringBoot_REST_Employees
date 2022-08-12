package wd.EmployeesREST.service;

import org.springframework.stereotype.Service;
import wd.EmployeesREST.Exceptions.ResourceNotFoundException;
import wd.EmployeesREST.dao.EmployeeRepository;
import wd.EmployeesREST.dto.Employee;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(long employee_id) throws ResourceNotFoundException {
        if (employeeRepository.findById(employee_id).isEmpty()) {
            throw new ResourceNotFoundException("There is no employee with id " + employee_id);
        }
        return employeeRepository.findById(employee_id).get();
    }

    public List<Employee> getByLastname(String lastname) throws ResourceNotFoundException {

        List<Employee> searchResult = employeeRepository.findByLastNameContainingIgnoreCase(lastname).stream().toList();

        if (searchResult.isEmpty()) {
            throw new ResourceNotFoundException("There is no employee with lastname " + lastname);
        }
        return searchResult;
    }

    public void add(Employee newEmployee) throws ResourceNotFoundException {
        if (newEmployee == null) {
            throw new IllegalArgumentException();
        }
        employeeRepository.save(newEmployee);
    }


    public void delete(Long employeeToDeleteId) throws ResourceNotFoundException {
        if (employeeRepository.findById(employeeToDeleteId).isEmpty()) {
            throw new ResourceNotFoundException("There is no employee with id " + employeeToDeleteId);
        }
        employeeRepository.deleteById(employeeToDeleteId);
    }

    public void update(Employee updatedEmployee, long employeeId) throws ResourceNotFoundException {

        Optional<Employee> checkIfInDB = employeeRepository.findById(employeeId);
        if (checkIfInDB.isPresent()) {

            employeeRepository.save(Employee.builder().employeeID(employeeId).firstName(updatedEmployee.getFirstName()).lastName(updatedEmployee.getLastName())
                    .dateOfBirth(updatedEmployee.getDateOfBirth()).gender(updatedEmployee.getGender()).departmentID(updatedEmployee.getDepartmentID()).jobTitle(updatedEmployee.getJobTitle()).build());

        } else throw new ResourceNotFoundException("There is no employee with id " + employeeId);
    }
}
