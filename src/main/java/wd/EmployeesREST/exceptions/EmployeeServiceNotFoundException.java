package wd.EmployeesREST.exceptions;

public class EmployeeServiceNotFoundException extends RuntimeException {
    public EmployeeServiceNotFoundException(String msg) {
        super(msg);
}
}