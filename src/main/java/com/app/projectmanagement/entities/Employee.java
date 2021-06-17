package com.app.projectmanagement.entities;

import com.app.projectmanagement.validators.UniqueValue;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
public class Employee {

    @Id
    @SequenceGenerator(name="employee_seq", sequenceName="employee_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employee_seq")
    private long employeeId;

    @NotBlank(message="*Must give a first name")
    @Size(min=2, max=50)
    private String firstName;

    @NotBlank(message="*Must give a last name")
    @Size(min=1, max=50)
    private String lastName;

    @NotBlank
    @Email(message="*Must be a valid email address")
    @UniqueValue
    private String email;



    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name="project_employee",
            joinColumns=@JoinColumn(name="employee_id"),
            inverseJoinColumns= @JoinColumn(name="project_id")
    )
    @JsonIgnore
    private List<Project> projects;
    public Employee() {

    }

    public Employee(String firstName, String lastName, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

