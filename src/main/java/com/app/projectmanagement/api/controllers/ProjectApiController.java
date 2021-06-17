package com.app.projectmanagement.api.controllers;


import com.app.projectmanagement.dao.ProjectRepository;
import com.app.projectmanagement.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;







@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {
	
	@Autowired
	ProjectRepository proRepo;
	
	@GetMapping
	public Iterable<Project> getProjects(){
		return proRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable("id") Long id) {
		return proRepo.findById(id).get();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project create(@RequestBody Project project) {
		return proRepo.save(project);
	}
	
	@PutMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Project update(@RequestBody Project project) {
		return proRepo.save(project);
	}
	
	@PatchMapping(path="/{id}", consumes= "application/json")
	public Project partialUpdate(@PathVariable("id") long id, @RequestBody Project patchProject) {
		Project project = proRepo.findById(id).get();
		
		if(patchProject.getName() != null) {
			project.setName(patchProject.getName());
		}
		if(patchProject.getStage() != null) {
			project.setStage(patchProject.getStage());
		}
		if(patchProject.getDescription() != null) {
			project.setDescription(patchProject.getDescription());
		}
		
		return proRepo.save(project);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		try {
			proRepo.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			
		}
	}
	
}
