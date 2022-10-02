package com.EmployeesREST.jms;

import com.EmployeesREST.config.JmsConfig;
import com.EmployeesREST.dao.EmployeeRepository;
import com.EmployeesREST.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmployeeJmsConsumer {
    private final EmployeeRepository employeeRepository;
    public EmployeeJmsConsumer(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @JmsListener(destination = JmsConfig.EMPLOYEES_QUEUE)
    public void consumeMessage(Employee employee) {
        log.info("IN: ConsumeMessage received employee {}", employee);
        employeeRepository.save(employee);
        log.info("OUT: Employee added: {}", employee);
    }

/*    @JmsListener(destination = JmsConfig.EMPLOYEES_QUEUE)
    public void consumeMessage(String employee) {
        log.info("Message received from activemq queue: " + employee);
        System.out.println(employee);
    }*/
}
