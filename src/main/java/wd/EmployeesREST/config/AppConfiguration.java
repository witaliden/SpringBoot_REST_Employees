package wd.EmployeesREST.config;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import wd.EmployeesREST.dao.EmployeeRepository;
import wd.EmployeesREST.dto.*;
import java.util.concurrent.TimeUnit;

@Configuration
class AppConfiguration {
    private static final Logger log = LoggerFactory.getLogger(AppConfiguration.class);

    @Bean("threadPoolTaskExecutor")
    public TaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(1000);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("WD_");
        return executor;
    }


    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            if(repository.count() < 5){
                Faker faker = new Faker();
                for(int i = 0; i < 10; i++){
                    log.info("Preloading " + repository.save(new Employee(faker.name().firstName(), faker.name().lastName(),
                            faker.number().numberBetween(1, 9), faker.job().title(), Gender.values()[faker.number().numberBetween(0,1)], faker.date().past(6580, TimeUnit.DAYS))));
                    //log.info("Preloading " + repository.save(Employee.builder().firstName(faker.name().firstName()).lastName(faker.name().lastName()).departmentID(faker.number().numberBetween(1,9))
                        //.dateOfBirth(faker.date().past(6580, TimeUnit.DAYS)).jobTitle(faker.job().title()).gender(Gender.values()[faker.number().numberBetween(0,1)]).build()));
                }
            }
        };
    }
}