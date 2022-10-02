package com.EmployeesREST.jms;

import com.EmployeesREST.config.JmsConfig;
import com.EmployeesREST.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmployeeJmsProducer {
    private final JmsTemplate jmsTemplate;
    public EmployeeJmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void addEmployee(Employee employee) {
        log.info("IN: Producer received employee: {}", employee);
        jmsTemplate.convertAndSend(JmsConfig.EMPLOYEES_QUEUE, employee);
        log.info("OUT: employee - {} - was converted and sent", employee);
    }
}