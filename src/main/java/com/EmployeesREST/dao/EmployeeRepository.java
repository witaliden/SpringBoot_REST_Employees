package com.EmployeesREST.dao;

import com.EmployeesREST.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(String lastName, String firstName);

}
