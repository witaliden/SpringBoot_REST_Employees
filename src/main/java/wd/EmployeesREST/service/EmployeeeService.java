package wd.EmployeesREST.service;

import org.springframework.stereotype.Service;
import wd.EmployeesREST.Exceptions.ResourceNotFoundException;
import wd.EmployeesREST.dao.EmployeeRepository;
import wd.EmployeesREST.dto.Employee;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeeService(EmployeeRepository employeeRepository) {
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

    public void add(Employee newEmployee){
        employeeRepository.save(newEmployee);
    }


    public void delete(Long employeeToDeleteId){
        if(employeeRepository.findById(employeeToDeleteId).isEmpty()){
            throw new ResourceNotFoundException("There is no employee with id " + employeeToDeleteId);
        }
            employeeRepository.deleteById(employeeToDeleteId);
    }

    public void update(Employee updatedEmployee) {

        Optional<Employee> checkIfInDB = employeeRepository.findById(updatedEmployee.getEmployee_id());
        if (checkIfInDB.isPresent()) {
            Employee tempEmployee = checkIfInDB.get();
            if(!updatedEmployee.getFirst_name().isEmpty()) {
                 tempEmployee.setFirst_name(updatedEmployee.getFirst_name());
            }
            if(!updatedEmployee.getLast_name().isEmpty()) {
                 tempEmployee.setLast_name(updatedEmployee.getLast_name());
            }
            if(updatedEmployee.getDate_of_birth() != null) {
                 tempEmployee.setDate_of_birth(updatedEmployee.getDate_of_birth());
            }
            if(!updatedEmployee.getJob_title().isEmpty()) {
                 tempEmployee.setJob_title(updatedEmployee.getJob_title());
            }
            if(updatedEmployee.getGender() != null) {
                 tempEmployee.setGender(updatedEmployee.getGender());
            }
            if(updatedEmployee.getDepartment_id() != 0) {
                 tempEmployee.setDepartment_id(updatedEmployee.getDepartment_id());
            }
            employeeRepository.save(tempEmployee);
        } else throw new ResourceNotFoundException("There is no employee with id " + updatedEmployee);
    }
}
