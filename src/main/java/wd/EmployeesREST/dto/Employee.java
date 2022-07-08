package wd.EmployeesREST.dto;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Employee")
public class Employee {
    private Long employee_id;
    private String first_name;
    private String last_name;
    //private int department_id;
   // private String job_title;
    private Gender gender;
    //private Date date_of_birth;

    public Employee() {
    }
    public Employee(String first_name, String last_name, String job_title, Gender gender, Date date_of_birth) {
        this.first_name = first_name;
        this.last_name = last_name;
        //this.job_title = job_title;
        this.gender = gender;
        //this.date_of_birth = date_of_birth;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getEmployee_id() {
        return employee_id;
    }
    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Column(name = "last_name", nullable = false)
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
//    @Column(name = "department_id", nullable = true)
//    public int getDepartment_id() {
//        return department_id;
//    }
//    public void setDepartment_id(int department_id) {
//        this.department_id = department_id;
//    }
//    @Column(name = "job_title", nullable = true)
//    public String getJob_title() {
//        return job_title;
//    }
//    public void setJob_title(String job_title) {
//        this.job_title = job_title;
//    }
    @Column(name = "gender", nullable = false)
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    @Column(name = "Date_of_birth", nullable = true)
//    public Date getDate_of_birth() {
//        return date_of_birth;
//    }
//    public void setDate_of_birth(Date date_of_birth) {
//        this.date_of_birth = date_of_birth;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Employee))
            return false;
        Employee employee = (Employee) o;
        return Objects.equals(this.employee_id, employee.employee_id) && Objects.equals(this.first_name, employee.first_name)
                && Objects.equals(this.last_name, employee.last_name) && Objects.equals(this.gender, employee.gender);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.employee_id, this.first_name, this.last_name, this.gender);
    }
    @Override
    public String toString() {
        return "Employee { " + "id = " + this.employee_id + ", name = '" + this.first_name + ", lastName = '" + this.last_name + '\'' + ", gender = '" + this.gender + '\'' + " }";
    }
}
