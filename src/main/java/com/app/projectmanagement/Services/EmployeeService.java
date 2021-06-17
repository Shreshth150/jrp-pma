package com.app.projectmanagement.Services;

import com.app.projectmanagement.dao.EmployeeRepository;
import com.app.projectmanagement.dto.EmployeeProject;
import com.app.projectmanagement.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository empRepo;

    public Employee save(Employee employee) {
        return empRepo.save(employee);
    }
    public Iterable<Employee> getAll() {
        return empRepo.findAll();
    }
    public List<EmployeeProject> employeeProjects() {
        return empRepo.employeeProjects();
    }

    public Employee getEmployeeById(Long id){
        return empRepo.findById(id).get();
    }

    public void deleteById(Long id){
        empRepo.deleteById(id);
    }

    public Employee findByEmployeeId(long theId) {
        // TODO Auto-generated method stub
        return empRepo.findByEmployeeId(theId);
    }


    public void delete(Employee theEmp) {
        empRepo.delete(theEmp);

    }



}
