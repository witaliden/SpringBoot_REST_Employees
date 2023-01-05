package com.employees.dao

import com.employees.dto.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.validation.constraints.Size


@Repository
interface EmployeeRepository : JpaRepository<Employee?, Long?> {
    fun findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(
        lastName: String?,
        firstName: String?
    ): MutableList<Employee?>?

    fun deleteEmployeesByEmployeeID(employeeId: Long?): Long?
    fun findAllByEmployeeIDBetweenOrderByEmployeeIDAsc(employeeID: Long?, employeeID2: Long?): MutableList<Employee?>?
    fun findByFirstNameLike(
        firstName: @Size(
            min = 2,
            max = 30,
            message = "Firstname should beу 2-50 characters long."
        ) String?
    ): List<Employee?>?
}
