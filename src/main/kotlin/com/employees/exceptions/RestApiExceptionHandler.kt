package com.employees.exceptions

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.util.*
import java.util.stream.Collectors
import javax.validation.ConstraintViolationException
import mu.KotlinLogging

private val log = KotlinLogging.logger {}

@RestControllerAdvice
class RestApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun methodArgumentTypeMismatchExceptionHandler(methodArgumentTypeMismatchException: MethodArgumentTypeMismatchException): String {
        log.error("MethodArgumentTypeMismatch exception: ", methodArgumentTypeMismatchException)
        return String.format(
            "%s parameter's type should be: %s. Your parameter: %s",
            methodArgumentTypeMismatchException.name,
            Objects.requireNonNull(methodArgumentTypeMismatchException.requiredType)!!.simpleName,
            methodArgumentTypeMismatchException.value
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
        MethodArgumentNotValidException::class
    )
    fun methodArgumentNotValidExceptionHandler(methodArgumentNotValidException: MethodArgumentNotValidException): String {
        val methodArgumentNotValidExceptionMessages = methodArgumentNotValidException.bindingResult.fieldErrors.stream()
            .map { err: FieldError ->
                String.format(
                    "Validation error coloured\n field: %s\n Your value: %s\n Reason: %s",
                    err.field,
                    err.rejectedValue,
                    err.defaultMessage
                )
            }
            .distinct().toList()
        log.error("MethodArgumentNotValid exception occurred.", methodArgumentNotValidException)
        val result = methodArgumentNotValidExceptionMessages.stream().map { obj: String? ->
            java.lang.String.valueOf(
                obj
            )
        }.collect(Collectors.joining("\n"))
        return methodArgumentNotValidExceptionMessages.stream().map { obj: String? ->
            java.lang.String.valueOf(
                obj
            )
        }.collect(Collectors.joining("\n"))
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
        ConstraintViolationException::class
    )
    fun constraintViolationExceptionHandler(constraintViolationException: ConstraintViolationException): String? {
        val constraintViolationExceptionMessageList = constraintViolationException.message
        log.error("ConstraingViolationException: ", constraintViolationException)
        return constraintViolationExceptionMessageList
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
        HttpMessageNotReadableException::class
    )
    fun HttpMessageNotReadableExceptionHandler(msgNotReadable: HttpMessageNotReadableException): String? {
        log.error(
            "HttpMessageNotReadable exception occurred. Check json validation.",
            msgNotReadable
        )
        return Objects.requireNonNull(msgNotReadable.rootCause)?.message
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(
        EmployeeServiceNotFoundException::class
    )
    fun employeeServiceNotFoundExceptionHandler(employeeServiceNotFoundException: EmployeeServiceNotFoundException?): String {
        val employeeServiceNotFoundExceptionMessage = "Object not found"
        log.error(employeeServiceNotFoundExceptionMessage, employeeServiceNotFoundException)
        return employeeServiceNotFoundExceptionMessage
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(
        EmptyResultDataAccessException::class
    )
    fun EmptyResultDataAccessExceptionHandler(emptyResultDataAccessException: EmptyResultDataAccessException): String? {
        emptyResultDataAccessException.cause
        log.error("EmptyResultDataAccess exception occurred. ", emptyResultDataAccessException)
        return emptyResultDataAccessException.cause!!.message
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(
        Exception::class
    )
    fun globalExceptionHandler(e: Exception?): String {
        val exceptionMessage = "Oops, something's wrong"
        log.error(exceptionMessage, e)
        return exceptionMessage
    }
}