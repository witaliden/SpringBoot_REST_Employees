package com.EmployeesREST.dao;

import com.EmployeesREST.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Size;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(String lastName, String firstName);
    Long deleteEmployeesByEmployeeID(Long employeeId);
    List<Employee> findAllByEmployeeIDBetweenOrderByEmployeeIDAsc(Long employeeID, Long employeeID2);
    List<Employee> findByFirstNameLike(@Size(min = 2, max = 30, message = "Firstname should beу 2-50 characters long.") String firstName);


}
