package com.app.projectmanagement.Services;

import com.app.projectmanagement.dao.ProjectRepository;
import com.app.projectmanagement.dto.ChartData;
import com.app.projectmanagement.dto.TimeChartData;
import com.app.projectmanagement.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository proRepo;

    public Project save(Project project) {
        return proRepo.save(project);
    }


    public List<Project> getAll() {
        return proRepo.findAll();
    }

    public List<ChartData> getProjectStatus(){
        return proRepo.getProjectStatus();
    }

    public Project getProjectById(long id){
        return proRepo.findById(id).get();
    }

    public void deleteById(long id){
        proRepo.deleteById(id);
    }

    public List<TimeChartData> getTimeData(){
        return proRepo.getTimeData();
    }

    public void delete(Project project){
        proRepo.delete(project);
    }

}
