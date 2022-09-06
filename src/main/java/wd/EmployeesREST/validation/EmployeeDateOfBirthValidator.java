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
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        if (localDateToValidate == null)
            throw new IllegalArgumentException("Null type argument");
        sdf.setLenient(false);
        try {
            sdf.parse(String.valueOf(localDateToValidate));
        } catch (ParseException e) {
            log.info("Exception during date of birth parsing", e);
            //throw new IllegalDateFormatException("Use proper date format: yyyy-mm-dd");
        }
        return localDateToValidate.plusDays(daysBeforeBecomeSadAdult).isBefore(LocalDate.now());
    }

}