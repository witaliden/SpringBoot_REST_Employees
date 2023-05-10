package com.employees.jms

import com.employees.config.JmsConfig
import com.employees.dao.EmployeeRepository
import com.employees.dto.Employee
import mu.KotlinLogging
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.jms.annotation.JmsListener
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {}

@Component
@EntityScan
class EmployeeJmsConsumer(private val employeeRepository: EmployeeRepository, jmsTemplate: JmsTemplate) {
    @JmsListener(destination = JmsConfig.EMPLOYEES_QUEUE)
    fun consumeMessage(employee: Employee?) {
        log.info("IN: ConsumeMessage received employee {}", employee)
        employeeRepository.save(employee!!)
        log.info("OUT: Employee added: {}", employee)
    }
}