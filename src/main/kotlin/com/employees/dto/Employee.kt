package com.employees.dto

import com.employees.validation.CustomDateValidator
import com.fasterxml.jackson.annotation.JsonFormat

import javax.persistence.*
import javax.validation.constraints.*
import java.time.LocalDate
import java.util.Objects


@Entity
@Table(name = "employees")
class Employee : Comparable<Employee> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    var employeeID: Long = 0

    @Column(name = "first_name")
    @Size(min = 2, max = 30, message = "Firstname should beу 2-50 characters long.")
    var firstName: String = ""

    @Size(min = 2, max = 30, message = "Lastname should be 2-50 characters long.")
    @Column(name = "last_name", nullable = false)
    var lastName: String = ""

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    var gender: Gender = Gender.MALE

    @CustomDateValidator("Employee should be 18 y.o.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date_Of_Birth")
    var dateOfBirth: LocalDate = LocalDate.of(2000,1,1)

    @Column(name = "job_Title")
    var jobTitle: String = ""

    @Column(name = "department_ID")
    @Positive
    var departmentID: Int = 0

    //----- constructors
    constructor()
    constructor(
        firstName: String,
        lastName: String,
        gender: Gender,
        dateOfBirth: LocalDate,
        jobTitle: String,
        departmentID: Int
    ) {
        this.firstName = firstName
        this.lastName = lastName
        this.departmentID = departmentID
        this.jobTitle = jobTitle
        this.gender = gender
        this.dateOfBirth = dateOfBirth
    }

    constructor(
        employeeID: Long,
        firstName: String,
        lastName: String,
        gender: Gender,
        dateOfBirth: LocalDate,
        jobTitle: String,
        departmentID: Int
    ) {
        this.employeeID = employeeID
        this.firstName = firstName
        this.lastName = lastName
        this.departmentID = departmentID
        this.jobTitle = jobTitle
        this.gender = gender
        this.dateOfBirth = dateOfBirth
    }

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (other !is Employee)
            return false
        return (this.firstName == other.firstName && this.lastName == other.lastName && (this.dateOfBirth == other.dateOfBirth)
                && this.gender == other.gender && this.jobTitle == other.jobTitle && (this.departmentID == other.departmentID))
    }

    override fun compareTo(other: Employee): Int {
        if (this.employeeID < other.employeeID)
            return -1
        else if (this.employeeID > other.employeeID)
            return 1
        return 0
    }

    override fun hashCode(): Int {
        return Objects.hash(employeeID, firstName, lastName, gender, dateOfBirth, jobTitle, departmentID)
    }

    override fun toString(): String {
        return "Employee(employeeID=$employeeID, firstName='$firstName', lastName='$lastName', gender=$gender, dateOfBirth=$dateOfBirth, jobTitle='$jobTitle', departmentID=$departmentID)"
    }
}