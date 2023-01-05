package com.employees.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [EmployeeDateOfBirthValidator::class])
@Target(AnnotationTarget.FIELD)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class CustomDateValidator(
    val message: String = "Employee should be at least 18 y.o.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)