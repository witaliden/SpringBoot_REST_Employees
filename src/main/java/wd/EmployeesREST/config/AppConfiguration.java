package wd.EmployeesREST.config;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wd.EmployeesREST.dao.EmployeeRepository;
import wd.EmployeesREST.dto.*;
import java.util.concurrent.TimeUnit;

@Configuration
class AppConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AppConfiguration.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            if(repository.count() < 5){
                Faker faker = new Faker();
                for(int i = 0; i < 10; i++){
                    log.info("Preloading " + repository.save(new Employee(faker.name().firstName(), faker.name().lastName(),
                            faker.number().numberBetween(1, 9), faker.job().title(), Gender.values()[faker.number().numberBetween(0,1)], faker.date().past(6580, TimeUnit.DAYS))));
                }
            }
        };
    }
}