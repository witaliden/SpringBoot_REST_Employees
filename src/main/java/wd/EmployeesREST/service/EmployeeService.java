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

    /*//pagination
    PagingAndSortingRepository<Employee, Long> repository; // … get access to a bean
    Page<Employee> employees = repository.findAll(PageRequest.of(1, 20));*/

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(long employee_id) throws ResourceNotFoundException {
        if(employeeRepository.findById(employee_id).isEmpty()){
            throw new ResourceNotFoundException("There is no employee with id " + employee_id);
        }
        return employeeRepository.findById(employee_id).get();
    }

    public List<Employee> getByLastname(String lastname) throws ResourceNotFoundException {
        if(employeeRepository.findByLastName(lastname).isEmpty()){
            throw new ResourceNotFoundException("There is no employee with lastname " + lastname);
        }
        return employeeRepository.findByLastName(lastname).stream().toList();
    }
    public List<Employee> getByLastnameContaining(String lastname) throws ResourceNotFoundException {
        if(employeeRepository.findByLastNameContainingIgnoreCase(lastname).isEmpty()){
            throw new ResourceNotFoundException("There is no employee with lastname like " + lastname);
        }
        return employeeRepository.findByLastNameContainingIgnoreCase(lastname).stream().toList();
    }

    public void add(Employee newEmployee) throws ResourceNotFoundException {
        if (newEmployee == null){
            throw new IllegalArgumentException();
        }
        employeeRepository.save(newEmployee);
    }


    public void delete(Long employeeToDeleteId) throws ResourceNotFoundException {
        if(employeeRepository.findById(employeeToDeleteId).isEmpty()){
            throw new ResourceNotFoundException("There is no employee with id " + employeeToDeleteId);
        }
            employeeRepository.deleteById(employeeToDeleteId);
    }

    public void update(Employee updatedEmployee) throws ResourceNotFoundException {

        Optional<Employee> checkIfInDB = employeeRepository.findById(updatedEmployee.getEmployeeID());
        if (checkIfInDB.isPresent()) {

            /*employeeRepository.save(Employee.builder().employeeID(checkIfInDB.get().getEmployeeID()).firstName(updatedEmployee.getFirstName()).lastName(updatedEmployee.getLastName())
                    .dateOfBirth(updatedEmployee.getDateOfBirth()).jobTitle(updatedEmployee.getJobTitle()).gender(updatedEmployee.getGender()).build());*/

            Employee tempEmployee = checkIfInDB.get();
            if(!updatedEmployee.getFirstName().isEmpty()) {
                 tempEmployee.setFirstName(updatedEmployee.getFirstName());
            }
            if(!updatedEmployee.getLastName().isEmpty()) {
                 tempEmployee.setLastName(updatedEmployee.getLastName());
            }
            if(updatedEmployee.getDateOfBirth() != null) {
                 tempEmployee.setDateOfBirth(updatedEmployee.getDateOfBirth());
            }
            if(!updatedEmployee.getJobTitle().isEmpty()) {
                 tempEmployee.setJobTitle(updatedEmployee.getJobTitle());
            }
            if(updatedEmployee.getGender() != null) {
                 tempEmployee.setGender(updatedEmployee.getGender());
            }
            if(updatedEmployee.getDepartmentID() != 0) {
                 tempEmployee.setDepartmentID(updatedEmployee.getDepartmentID());
            }
            employeeRepository.save(tempEmployee);
        } else throw new ResourceNotFoundException("There is no employee with id " + updatedEmployee);
    }
}
