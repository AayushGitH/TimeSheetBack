package com.pro.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pro.api.entities.Client;
import com.pro.api.entities.Employee;
import com.pro.api.entities.Project;
import com.pro.api.model.AssignClient;
import com.pro.api.model.AssignProject;
import com.pro.api.service.ClientService;
import com.pro.api.service.EmployeeService;
import com.pro.api.service.ProjectService;

@CrossOrigin
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ClientService clientService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// ------------------------------------------------------| Demo |------------------------------------------------------
	@GetMapping("/first")
	public ResponseEntity<?> first() {
		return ResponseEntity.status(HttpStatus.OK)
				.body("I am after successfull login page and under the admin controller");
	}

	// ------------------------------------------------------| Add employee as a User |------------------------------------------------------
	@PostMapping("/addEmployee")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		employee.setRole("ROLE_USER");
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.employeeService.createEmployee(employee));
//			return ResponseEntity.status(HttpStatus.CREATED).body(employee);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Enter correct details !!");
		}
	}

	// ------------------------------------------------------| Update an employee |------------------------------------------------------
	@PatchMapping("/updateEmployee")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.employeeService.updateEmployee(employee));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
	}

	// ------------------------------------------------------| Read employees by status |------------------------------------------------------
	@GetMapping("/getEmployeesByStatus/{status}")
	public ResponseEntity<?> getEmployeesByStatus(@PathVariable("status") String Status) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.employeeService.getEmployeeByStatus(Status));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Something went wrong in server side !!!!!");
		}
	}
	
	// ------------------------------------------------------| Read employees by status |------------------------------------------------------
	@GetMapping("/getEmployees")
	public ResponseEntity<?> getEmployees() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.employeeService.getUserEmployees("ROLE_USER"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Something went wrong in server side !!!!!");
		}
	}

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
	
	// ------------------------------------------------------| Assign a project to Employee |------------------------------------------------------
	@PostMapping("/assignProject")
	public ResponseEntity<?> assignProjectToEmployee(@RequestBody AssignProject assignProject) {
		try {
			Employee employee = this.employeeService.readEmployeeById(assignProject.getEmpId());
			Project project = this.projectService.readProjectById(assignProject.getProjectId());
			employee.setProject(project);
			this.employeeService.updateEmployee(employee);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully assigned project");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Enter proper informations");
		}
	}
	
	// ------------------------------------------------------| Add a client |------------------------------------------------------
	@PostMapping("/addClient")
	public ResponseEntity<?> addClient(@RequestBody Client client) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.clientService.addClient(client));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Something went wrong in the server !!");
		}
	}
	
	// ------------------------------------------------------| Update a client |------------------------------------------------------
	@PatchMapping("/updateClient")
	public ResponseEntity<?> updateClient(@RequestBody Client client) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.clientService.updateClient(client));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
	}
	
	// ------------------------------------------------------| Read all clients |------------------------------------------------------
	@GetMapping("/readAllClients")
	public ResponseEntity<?> readAllClients() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.clientService.readClients());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Something went wrong in server side !!!!!");
		}
	}
	
	// ------------------------------------------------------| Assign a project to Client |------------------------------------------------------
	@PostMapping("/assignClient")
	public ResponseEntity<?> assignProjectToClient(@RequestBody AssignClient assignClient) {
		try {
			Client client = this.clientService.readClientById(assignClient.getProjectId());
			Project project = this.projectService.readProjectById(assignClient.getProjectId());
			project.setClient(client);
			this.projectService.updateProject(project);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully assigned project");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Enter proper informations");
		}
	}
}
