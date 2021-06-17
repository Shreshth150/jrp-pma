package com.app.projectmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity(name = "project")
public class Project {

    @Id
    @SequenceGenerator(name="project_seq", sequenceName="project_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="project_seq")
    private long projectId;

    private String name;

    private String stage; // NOTSTARTED, COMPLETED, INPROGRESS

    private String description;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @NotBlank(message="date cannot be empty")
    private Date startDate;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @NotBlank(message="date cannot be empty")
    private Date endDate;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name="project_employee",
            joinColumns=@JoinColumn(name="project_id"),
            inverseJoinColumns= @JoinColumn(name="employee_id")
    )
    @JsonIgnore
    private List<Employee> employees;

    public Project() {

    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Project(String name, String stage, String description) {
        super();
        this.name = name;
        this.stage = stage;
        this.description = description;
    }



    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // convenience method:
    public void addEmployee(Employee emp) {
        if(employees==null) {
            employees = new ArrayList<>();
        }
        employees.add(emp);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
