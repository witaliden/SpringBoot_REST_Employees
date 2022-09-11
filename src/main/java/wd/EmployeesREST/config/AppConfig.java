package wd.EmployeesREST.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
//@ConditionalOnClass(name = "SomeClass.class")
class AppConfig {

/*    @Bean
    @ConditionalOnProperty(name = "spring.datasource.url",
            havingValue = "jdbc:postgresql://localhost:5432/mastery")
    @ConditionalOnBean(name = "EmployeeRepository")
    @ConditionalOnMissingBean
    public void someBean() {
    }*/
}
