package wd.EmployeesREST.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
//@ConditionalOnClass(name = "SomeClass.class")
class AppConfig {

/*    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            ServerHttpSecurity http) {
        return http.authorizeExchange()
                .pathMatchers("/actuator/**").permitAll()
                .anyExchange().authenticated()
                .and().build();
    }*/

/*    @Bean
    @ConditionalOnProperty(name = "spring.datasource.url",
            havingValue = "jdbc:postgresql://localhost:5432/mastery")
    @ConditionalOnBean(name = "EmployeeRepository")
    @ConditionalOnMissingBean
    public void someBean() {
    }*/
}
