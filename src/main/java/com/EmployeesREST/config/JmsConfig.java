package com.EmployeesREST.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
@EnableJms
public class JmsConfig {
    public static final String EMPLOYEES_QUEUE = "employeesQueue";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("employeeService");
        converter.setObjectMapper(new ObjectMapper().registerModule(new JavaTimeModule().addSerializer(LocalDate.class, new LocalDateSerializer(FORMATTER))));
        return converter;
    }
}
