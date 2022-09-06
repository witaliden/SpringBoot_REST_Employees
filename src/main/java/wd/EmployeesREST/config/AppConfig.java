package wd.EmployeesREST.config;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import wd.EmployeesREST.dao.EmployeeRepository;

@Configuration
@PropertySource("classpath:application.properties")
@ConditionalOnClass(name = "SomeClass.class")
class AppConfig {

    @Bean
    @ConditionalOnProperty(name = "spring.datasource.url",
    havingValue = "jdbc:postgresql://localhost:5432/mastery")
    @ConditionalOnBean(name = "EmployeeRepository")
    @ConditionalOnMissingBean
    public void someBean(){
    }


/*
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            if(repository.count() < 5){
                Faker faker = new Faker();
                for(int i = 0; i < 10; i++){
                    repository.save(new Employee(faker.name().firstName(), faker.name().lastName(),
                            faker.number().numberBetween(1, 9), faker.job().title(), Gender.values()[faker.number().numberBetween(0,1)], faker.date().past(6580, TimeUnit.DAYS)));
                }
            }
        };
    }
 */
}
