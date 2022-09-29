package com.EmployeesREST.jms;

import com.EmployeesREST.dto.Employee;
import com.EmployeesREST.dto.Gender;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeMessageConverter implements MessageConverter {
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        Employee employee = (Employee) object;
        MapMessage message = session.createMapMessage();
        message.setString("firstName", employee.getFirstName());
        message.setString("lastName", employee.getLastName());
        message.setString("gender", String.valueOf(employee.getGender()));
        message.setString("dateOfBirth", employee.getDateOfBirth().toString());
        message.setString("jobTitle", employee.getJobTitle());
        message.setInt("departmentId", employee.getDepartmentID());
        return message;
    }

    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        MapMessage mapMessage = (MapMessage) message;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return new Employee(mapMessage.getLong("employeeID"),mapMessage.getString("firstName"), mapMessage.getString("lastName"), Gender.valueOf(mapMessage.getString("gender")),
                LocalDate.parse(mapMessage.getString("dateOfBirth"), formatter), mapMessage.getString("jobTitle"), mapMessage.getInt("departmentId"));
    }
}
