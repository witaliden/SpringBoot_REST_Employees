package wd.EmployeesREST.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestApiExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String badArgumentException(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.error("MethodArgumentNotValid exception occurred. ", methodArgumentNotValidException);
        return methodArgumentNotValidException.getMessage();
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeServiceNotFoundException.class)
    public String resourceNotFoundException(EmployeeServiceNotFoundException employeeServiceNotFoundException) {
        log.error("ResourceNotFound exception occurred. ", employeeServiceNotFoundException);
        return employeeServiceNotFoundException.getMessage();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String globalExceptionHandler(Exception e) {
        log.error("Some error occurred", e);
        return e.getMessage();
    }
}