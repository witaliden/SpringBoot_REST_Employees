package wd.EmployeesREST.config;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
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
    public void someBean() {
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
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
