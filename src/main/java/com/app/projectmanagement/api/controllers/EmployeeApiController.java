package com.app.projectmanagement.api.controllers;

import com.app.projectmanagement.Services.EmployeeService;
import com.app.projectmanagement.entities.Employee;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {

    private final EmployeeService employeeService;

    public EmployeeApiController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("{/id}")
    public Employee getEmployeeById(@PathVariable("id") Long id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Employee update(@RequestBody @Valid Employee employee){
        return employeeService.save(employee);
    }


    @PatchMapping(path = "{/id}", consumes = "application/json")
    public Employee partialUpdate(@PathVariable("id") long id , @RequestBody @Valid Employee patchEmployee){
        Employee emp = employeeService.getEmployeeById(id);

        if(patchEmployee.getEmail() != null) {
            emp.setEmail(patchEmployee.getEmail());
        }
        if(patchEmployee.getFirstName() != null) {
            emp.setFirstName(patchEmployee.getFirstName());
        }
        if(patchEmployee.getLastName() != null) {
            emp.setLastName(patchEmployee.getLastName());
        }
        return employeeService.save(emp);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        try {
            employeeService.deleteById(id);
        }
        catch(EmptyResultDataAccessException e) {

        }
    }

}
