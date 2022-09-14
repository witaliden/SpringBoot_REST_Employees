package wd.EmployeesREST.validation;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


@Slf4j
public class EmployeeDateOfBirthValidator implements
        ConstraintValidator<CustomDateValidator, LocalDate> {
    int daysBeforeBecomeSadAdult = 6575;
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    @Override
    public void initialize(CustomDateValidator customDate) {
    }

    @Override
    public boolean isValid(LocalDate localDateToValidate, ConstraintValidatorContext cxt) {

        return localDateToValidate.plusDays(daysBeforeBecomeSadAdult).isBefore(LocalDate.now());
    }

}