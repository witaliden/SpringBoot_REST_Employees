package com.employees.jms

import com.employees.config.JmsConfig
import com.employees.dao.EmployeeRepository
import com.employees.dto.Employee
import mu.KotlinLogging
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {}

@Component
class EmployeeJmsConsumer(employeeRepository: EmployeeRepository) {
    private val employeeRepository: EmployeeRepository

    init {
        this.employeeRepository = employeeRepository
    }

    @JmsListener(destination = JmsConfig.EMPLOYEES_QUEUE)
    fun consumeMessage(employee: Employee?) {
        log.info("IN: ConsumeMessage received employee {}", employee)
        employeeRepository.save(employee!!)
        log.info("OUT: Employee added: {}", employee)
    }
}