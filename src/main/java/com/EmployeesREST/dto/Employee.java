package com.EmployeesREST.dto;

import com.EmployeesREST.validation.CustomDateValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "employees")
public class Employee implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeID;
    @Column(name = "first_name")
    @Size(min = 2, max = 30, message = "Firstname should beу 2-50 characters long.")
    private String firstName;
    @Size(min = 2, max = 30, message = "Lastname should be 2-50 characters long.")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @CustomDateValidator
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date_Of_Birth")
    private LocalDate dateOfBirth;
    @Column(name = "job_Title")
    private String jobTitle;
    @Column(name = "department_ID")
    @Positive
    private int departmentID;

    //----- constructors
    public Employee() {
    }

    public Employee(String firstName, String lastName, Gender gender, LocalDate dateOfBirth, String jobTitle, int departmentID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentID = departmentID;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public Employee(Long employeeID, String firstName, String lastName, Gender gender, LocalDate dateOfBirth, String jobTitle, int departmentID) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentID = departmentID;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID, firstName, lastName, gender, dateOfBirth, jobTitle, departmentID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Employee that))
            return false;
        return firstName.equals(that.firstName) && lastName.equals(that.lastName) && dateOfBirth.equals(that.dateOfBirth)
                && gender.equals(that.gender) && jobTitle.equals(that.jobTitle) && departmentID == that.departmentID;
    }

    @Override
    public int compareTo(Object o) {
        if (this.employeeID < ((Employee) o).employeeID)
            return -1;
        else if (this.employeeID > ((Employee) o).employeeID)
            return 1;
        return 0;
    }
}
