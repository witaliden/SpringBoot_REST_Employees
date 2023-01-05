package com.employees.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.support.converter.MappingJackson2MessageConverter
import org.springframework.jms.support.converter.MessageConverter
import org.springframework.jms.support.converter.MessageType
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Configuration
@EnableJms
class JmsConfig {

    @Bean
    fun messageConverter(): MessageConverter {
        val converter = MappingJackson2MessageConverter()
        converter.setTargetType(MessageType.TEXT)
        converter.setTypeIdPropertyName("EmployeeService")
        converter.setObjectMapper(
            ObjectMapper().registerModule(
                JavaTimeModule().addSerializer(
                    LocalDate::class.java, LocalDateSerializer(
                        FORMATTER
                    )
                )
            )
        )
        return converter
    }

    companion object {
        const val EMPLOYEES_QUEUE = "employeesQueue"
        val FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }
}