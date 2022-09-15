package com.EmployeesREST.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class RestApiExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.error("MethodArgumentNotValid exception occurred. ", methodArgumentNotValidException);
        return methodArgumentNotValidException.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String constraintViolationException(ConstraintViolationException constraintViolationException) {
        log.error("ConstraintViolation exception occurred. ", constraintViolationException);
        return constraintViolationException.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String httpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException) {
        String httpMessageNotReadableExceptionMessage = "Wrong parameter. Probably date is not formatted like 2000-01-01";
        log.error(httpMessageNotReadableExceptionMessage, httpMessageNotReadableException);
        return httpMessageNotReadableExceptionMessage;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeServiceNotFoundException.class)
    public String employeeServiceNotFoundException(EmployeeServiceNotFoundException employeeServiceNotFoundException) {
        String employeeServiceNotFoundExceptionMessage = "Object not found";
        log.error(employeeServiceNotFoundExceptionMessage, employeeServiceNotFoundException);
        return employeeServiceNotFoundExceptionMessage;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String globalExceptionHandler(Exception e) {
        String exceptionMessage = "Internal server error";
        log.error(exceptionMessage, e);
        return exceptionMessage;
    }
}
