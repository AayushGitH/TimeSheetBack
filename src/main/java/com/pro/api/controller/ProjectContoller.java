package com.pro.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.api.entities.Employee;
import com.pro.api.entities.Project;
import com.pro.api.model.AssignProject;
import com.pro.api.service.EmployeeService;
import com.pro.api.service.ProjectService;

@CrossOrigin
@RestController
@RequestMapping("/project")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class ProjectContoller 
{
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private EmployeeService employeeService;
	
		// ------------------------------------------------------| Add project |------------------------------------------------------
		@PostMapping("/addProject")
		public ResponseEntity<?> addProject(@RequestBody Project project) {
			try {
				return ResponseEntity.status(HttpStatus.CREATED).body(this.projectService.addProject(project));

			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Enter correct details !!");
			}
		}

		// ------------------------------------------------------| Update a project |------------------------------------------------------
		@PatchMapping("/updateProject")
		public ResponseEntity<?> updateProject(@RequestBody Project project) {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(this.projectService.updateProject(project));
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(e.getMessage());
			}
		}
		
		// ------------------------------------------------------| Read all projects |------------------------------------------------------
		@GetMapping("/readAllProjects")
		public ResponseEntity<?> readAllProjects() {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(this.projectService.readProjects());
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Something went wrong in server side !!!!!");
			}
		}
		
		// ------------------------------------------------------| Read all projects |------------------------------------------------------
		@GetMapping("/readProject/{projectId}")
		public ResponseEntity<?> readProjectById(@PathVariable("projectId") int projectId) {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(this.projectService.readProjectById(projectId));
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Something went wrong in server side !!!!!");
			}
		}
		
		// ------------------------------------------------------| Assign a project to Employee |------------------------------------------------------
		@PostMapping("/assignProject")
		public ResponseEntity<?> assignProjectToEmployee(@RequestBody AssignProject assignProject) {
			try {
				Employee employee = this.employeeService.readEmployeeById(assignProject.getEmpId());
				Project project = this.projectService.readProjectById(assignProject.getProjectId());
				employee.setStatus("Active");
				employee.setProject(project);
				this.employeeService.updateEmployee(employee);
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully assigned project");
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Enter proper informations");
			}
		}
}
