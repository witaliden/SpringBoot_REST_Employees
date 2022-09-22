package com.EmployeesREST.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class RestApiExceptionHandler {
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException, WebRequest request) {
        log.error("MethodArgumentTypeMismatch exception: ", methodArgumentTypeMismatchException);
        return String.format("%s parameter's type should be: %s. Your parameter: %s", methodArgumentTypeMismatchException.getName(),
                Objects.requireNonNull(methodArgumentTypeMismatchException.getRequiredType()).getSimpleName(), methodArgumentTypeMismatchException.getValue());
    }

/*
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public String numberFormatExceptionHandler(NumberFormatException numberFormatException) {
        log.error("NumberFormat exception: ", numberFormatException);
        return numberFormatException.getCause().toString();
    }
*/

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<String> methodArgumentNotValidExceptionMessages = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
                .map(err -> String.format("Validation error coloured\n field: %s\n Your value: %s\n Reason: %s", err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                .distinct().toList();
        log.error("MethodArgumentNotValid exception occurred.", methodArgumentNotValidException);
        String result = methodArgumentNotValidExceptionMessages.stream().map(String::valueOf).collect(Collectors.joining("\n"));
        return methodArgumentNotValidExceptionMessages.stream().map(String::valueOf).collect(Collectors.joining("\n"));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String constraintViolationExceptionHandler(ConstraintViolationException constraintViolationException) {
        String constraintViolationExceptionMessageList = constraintViolationException.getCause().getMessage();
        log.error("ConstraingViolationException: ", constraintViolationException);
        return constraintViolationExceptionMessageList;
    }


    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException msgNotReadable) {
        log.error("HttpMessageNotReadable exception occurred. Check json validation.", msgNotReadable);
        return Objects.requireNonNull(msgNotReadable.getRootCause()).getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeServiceNotFoundException.class)
    public String employeeServiceNotFoundExceptionHandler(EmployeeServiceNotFoundException employeeServiceNotFoundException) {
        String employeeServiceNotFoundExceptionMessage = "Object not found";
        log.error(employeeServiceNotFoundExceptionMessage, employeeServiceNotFoundException);
        return employeeServiceNotFoundExceptionMessage;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String EmptyResultDataAccessExceptionHandler(EmptyResultDataAccessException emptyResultDataAccessException) {
        emptyResultDataAccessException.getCause();
        log.error("EmptyResultDataAccess exception occurred. ", emptyResultDataAccessException);
        return emptyResultDataAccessException.getCause().getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String globalExceptionHandler(Exception e) {
        String exceptionMessage = "Oops, something's wrong";
        log.error(exceptionMessage, e);
        return exceptionMessage;
    }
}
