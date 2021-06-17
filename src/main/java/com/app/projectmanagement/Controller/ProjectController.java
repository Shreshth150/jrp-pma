package com.app.projectmanagement.Controller;

import com.app.projectmanagement.Services.EmployeeService;
import com.app.projectmanagement.Services.ProjectService;
import com.app.projectmanagement.dto.TimeChartData;
import com.app.projectmanagement.entities.Employee;
import com.app.projectmanagement.entities.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectService proService;

	@Autowired
	EmployeeService empService;

	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects = proService.getAll();
		model.addAttribute("projects", projects);
		return "projects/list-projects";
	}


	@GetMapping("/new")
	public String displayProjectForm(Model model) {

		Project aProject = new Project();
		Iterable<Employee> employees = empService.getAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);

		return "projects/new-project";
	}

	@PostMapping("/save")
	public String createProject(Project project, @RequestParam List<Long> employees, Model model) {

		proService.save(project);

		// use a redirect to prevent duplicate submissions
		return "redirect:/projects ";

	}

	@GetMapping("/update")
	public String displayProjectUpdateForm(@RequestParam("id") long theId, Model model){

		Project theProj = proService.getProjectById(theId);
		Iterable<Employee> employees = empService.getAll();

		Date startDate = theProj.getStartDate();
		theProj.setStartDate(startDate);
// 		Date endDate = theProj.getEndDate();
		theProj.setEndDate(null);
		model.addAttribute("allEmployees",employees);
		model.addAttribute("project" , theProj);

		return "projects/update-project";
	}


	@PostMapping("/perform-update")
	public String updateProject(Project newProject , @RequestParam List<Long> employees, Model model){

		long proId = newProject.getProjectId();
		Project project = proService.getProjectById(proId);
		project.setName(newProject.getName());
		project.setStage(newProject.getStage());
		project.setDescription(newProject.getDescription());
		project.setEmployees(newProject.getEmployees());
		project.setStartDate(newProject.getStartDate());
		project.setEndDate(newProject.getEndDate());
		proService.save(project);
		return "redirect:/projects" ;

	}

	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") long theId, Model model) {
		Project theProj = proService.getProjectById(theId);
		proService.delete(theProj);
		return "redirect:/projects" ;
	}

	@GetMapping("/timelines")
	public String displayProjectTimelines(Model model) throws JsonProcessingException {

		List<TimeChartData> timelineData = proService.getTimeData();

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTimelineString = objectMapper.writeValueAsString(timelineData);

		System.out.println("---------- project timelines ----------");
		System.out.println(jsonTimelineString);

		model.addAttribute("projectTimeList", jsonTimelineString);

		return "projects/project-timelines";
	}

}
