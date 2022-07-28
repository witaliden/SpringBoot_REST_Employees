package wd.EmployeesREST.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Builder
@Entity
@Table(name = "Employee")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeID;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "departmentID")
    private int departmentID;
    @Column(name = "jobTitle")
    private String jobTitle;
    @Column(name = "gender", nullable = false)
    private Gender gender;
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    //----- constructors
    public Employee() {
    }
    public Employee(String firstName, String lastName, int departmentID, String jobTitle, Gender gender, Date dateOfBirth)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentID = departmentID;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }
    public Employee(Long employeeID, String firstName, String lastName, int departmentID, String jobTitle, Gender gender, Date dateOfBirth)
    {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentID = departmentID;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }


    //----- override methods of Object class
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Employee employee))
            return false;
        return Objects.equals(this.employeeID, employee.employeeID) && Objects.equals(this.firstName, employee.firstName)
                && Objects.equals(this.lastName, employee.lastName) && Objects.equals(this.gender, employee.gender);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.employeeID, this.firstName, this.lastName, this.gender);
    }
    @Override
    public String toString() {
        return "Employee { " + "id = " + this.employeeID + ", name = '" + this.firstName + ", lastName = '" + this.lastName + '\'' + ", gender = '" + this.gender + '\'' + " }";
    }
}
