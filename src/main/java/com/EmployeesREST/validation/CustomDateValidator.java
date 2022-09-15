package com.EmployeesREST.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmployeeDateOfBirthValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomDateValidator {
    String message() default "Employee should be at least 18 y.o.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}