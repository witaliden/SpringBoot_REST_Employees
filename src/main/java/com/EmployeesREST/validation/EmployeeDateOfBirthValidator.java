package com.EmployeesREST.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class EmployeeDateOfBirthValidator implements
        ConstraintValidator<CustomDateValidator, LocalDate> {
    int daysBeforeBecomeSadAdult = 6575;
    @Override
    public void initialize(CustomDateValidator customDate) {
    }

    @Override
    public boolean isValid(LocalDate localDateToValidate, ConstraintValidatorContext cxt) {

        return localDateToValidate.plusDays(daysBeforeBecomeSadAdult).isBefore(LocalDate.now());
    }

}