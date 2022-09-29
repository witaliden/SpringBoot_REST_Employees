package com.EmployeesREST.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
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
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    // First
    @Bean
    public MessageConverter messageConverter() {
        new Jackson2JsonMessageConverter(new ObjectMapper());
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setObjectMapper(objectMapper());
        return converter;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);

        JavaTimeModule timeModule = new JavaTimeModule();
        LocalDateSerializer localDateSerializer = new LocalDateSerializer(FORMATTER);
        timeModule.addSerializer(LocalDate.class, localDateSerializer);
        mapper.registerModule(timeModule);
        return mapper;
    }

/*    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> builder.serializationInclusion(JsonInclude.Include.NON_NULL)
                .serializers(LOCAL_DATE_SERIALIZER);
    }*/

/*    @Bean
    public Jackson2JsonMessageConverter messageConverter()
    {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        return new Jackson2JsonMessageConverter(mapper);
    }*/

}
