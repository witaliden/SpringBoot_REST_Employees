package com.employees.service

import com.employees.dao.EmployeeRepository
import com.employees.dto.Employee
import com.employees.exceptions.EmployeeServiceNotFoundException
import com.employees.jms.EmployeeJmsProducer
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class EmployeeService(employeeRepository: EmployeeRepository, producer: EmployeeJmsProducer) {
    private val employeeRepository: EmployeeRepository
    private val producer: EmployeeJmsProducer

    init {
        this.employeeRepository = employeeRepository
        this.producer = producer
    }

    @Throws(EmployeeServiceNotFoundException::class)
    fun getEmployeeById(employeeID: Long): Employee? {
        return employeeRepository.findById(employeeID).orElseThrow {
            EmployeeServiceNotFoundException(
                String.format(
                    "Employee with id %d does not exist!",
                    employeeID
                )
            )
        }
    }

    @Throws(EmployeeServiceNotFoundException::class)
    fun getEmployeesByLastAndFirstName(lastName: String?, firstName: String?): List<Employee?>? {
        return employeeRepository.findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(
            lastName,
            firstName
        )
    }

    fun addEmployee(newEmployee: Employee?) {
        employeeRepository.save(newEmployee!!)
    }

    @Throws(EmployeeServiceNotFoundException::class)
    fun deleteEmployee(employeeToDeleteId: Long?) {
        employeeRepository.deleteEmployeesByEmployeeID(employeeToDeleteId)
    }

    @Transactional
    @Throws(EmployeeServiceNotFoundException::class)
    fun updateEmployee(updatedEmployee: Employee, employeeId: Long) {
        employeeRepository.findById(employeeId).orElseThrow {
            EmployeeServiceNotFoundException(
                String.format(
                    "Employee with id %d does not exist!",
                    employeeId
                )
            )
        }
        employeeRepository.save(Employee(employeeId, updatedEmployee.firstName, updatedEmployee.lastName, updatedEmployee.gender, updatedEmployee.dateOfBirth,
        updatedEmployee.jobTitle, updatedEmployee.departmentID))
    }

    fun addEmployeeUsingJMS(employee: Employee?) {
        producer.addEmployee(employee)
    }
}