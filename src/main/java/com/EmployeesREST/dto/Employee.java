package com.EmployeesREST.dto;

import com.EmployeesREST.validation.CustomDateValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@Entity
@Table(name = "Employees")
public class Employee implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeID;
    @Column(name = "first_name")
    @Size(min =2, max = 30, message = "Firstname should beу 2-50 characters long.")
    private String firstName;
    @Size(min =2, max = 30, message = "Lastname should be 2-50 characters long.")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "department_ID")
    @Positive
    private int departmentID;
    @Column(name = "job_Title")
    private String jobTitle;
    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @CustomDateValidator
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date_Of_Birth")
    private LocalDate dateOfBirth;

    //----- constructors
    public Employee() {
    }

    public Employee(String firstName, String lastName, int departmentID, String jobTitle, Gender gender, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentID = departmentID;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public Employee(Long employeeID, String firstName, String lastName, int departmentID, String jobTitle, Gender gender, LocalDate dateOfBirth) {
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
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(firstName);
        hcb.append(lastName);
        hcb.append(gender);
        hcb.append(dateOfBirth);
        hcb.append(departmentID);
        hcb.append(jobTitle);
        return hcb.toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Employee that))
            return false;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(firstName, that.firstName);
        eb.append(lastName, that.lastName);
        eb.append(dateOfBirth, that.dateOfBirth);
        eb.append(gender, that.gender);
        eb.append(jobTitle, that.jobTitle);
        eb.append(departmentID, that.departmentID);
        return eb.isEquals();
    }

    @Override
    public int compareTo(Object o) {
        if (this.employeeID < ((Employee) o).employeeID)
            return -1;
        else if (this.employeeID > ((Employee) o).employeeID)
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Employee %d, %s %s (%s) born on %s. Works as %s at department %d", employeeID, firstName, lastName, gender, dateOfBirth, jobTitle, departmentID);
    }
}