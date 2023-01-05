package com.employees.jms

import com.employees.config.JmsConfig
import com.employees.dto.Employee
import mu.KotlinLogging
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {}

@Component
class EmployeeJmsProducer(private val jmsTemplate: JmsTemplate) {
    fun addEmployee(employee: Employee?) {
        log.info("IN: Producer received employee: {}", employee)
        if (employee != null) {
            jmsTemplate.convertAndSend(JmsConfig.EMPLOYEES_QUEUE, employee)
        }
        log.info("OUT: employee - {} - was converted and sent", employee)
    }
}