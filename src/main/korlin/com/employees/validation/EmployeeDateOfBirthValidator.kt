package com.employees.validation

import java.time.LocalDate
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmployeeDateOfBirthValidator : ConstraintValidator<CustomDateValidator?, LocalDate> {
    var daysBeforeBecomeSadAdult = 6575
    override fun initialize(customDate: CustomDateValidator?) {}
    override fun isValid(localDateToValidate: LocalDate, cxt: ConstraintValidatorContext): Boolean {
        return localDateToValidate.plusDays(daysBeforeBecomeSadAdult.toLong()).isBefore(LocalDate.now())
    }
}