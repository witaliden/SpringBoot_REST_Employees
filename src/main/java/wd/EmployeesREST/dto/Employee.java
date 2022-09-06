package wd.EmployeesREST.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import wd.EmployeesREST.validation.CustomDateValidator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Builder
@Data
@Entity
@Validated
@Table(name = "Employees")
public class Employee implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeID;
    @Column(name = "first_name")
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name should be between 10 and 50 characters")
    private String firstName;
    @NotBlank(message = "Lastname is mandatory")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "department_ID")
    @PositiveOrZero
    private int departmentID;
    @Column(name = "job_Title")
    private String jobTitle;
    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @CustomDateValidator
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
    public int compareTo(Object o) {
        if (this.employeeID < ((Employee) o).employeeID)
            return -1;
        else if (this.employeeID > ((Employee) o).employeeID)
            return 1;
        return 0;
    }
}
