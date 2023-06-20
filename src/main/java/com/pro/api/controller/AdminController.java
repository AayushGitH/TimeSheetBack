package com.pro.api.controller;

import java.util.List;

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
import com.pro.api.entities.Holiday;
import com.pro.api.entities.Project;
import com.pro.api.model.AssignClient;
import com.pro.api.model.AssignProject;
import com.pro.api.repo.HolidayRepo;
import com.pro.api.repo.TimeSheetRepo;
import com.pro.api.service.ClientService;
import com.pro.api.service.EmployeeService;
import com.pro.api.service.HolidayService;
import com.pro.api.service.ProjectService;

import jakarta.validation.Valid;

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
	private HolidayService holidayService;	
	
	@Autowired
	private TimeSheetRepo timeSheetRepo;
	
	@Autowired
	private HolidayRepo holidayRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

//	// ------------------------------------------------------| Demo |------------------------------------------------------
//	@GetMapping("/first")
//	public ResponseEntity<?> first() {
//		return ResponseEntity.status(HttpStatus.OK)
//				.body("I am after successfull login page and under the admin controller");
//	}
//
//	// ------------------------------------------------------| Add employee as a User |------------------------------------------------------
//	@PostMapping("/addEmployee")
//	public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee) {
//		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
//		employee.setStatus("Inactive");
//		employee.setRole("ROLE_USER");
//		try {
////			return ResponseEntity.status(HttpStatus.CREATED).body(this.employeeService.createEmployee(employee));
//			return ResponseEntity.status(HttpStatus.CREATED).body(employee);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Enter correct details !!");
//		}
//	}
//
//	// ------------------------------------------------------| Update an employee |------------------------------------------------------
//	@PatchMapping("/updateEmployee")
//	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
//		try {
//			return ResponseEntity.status(HttpStatus.OK).body(this.employeeService.updateEmployee(employee));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body(e.getMessage());
//		}
//	}
//
//	// ------------------------------------------------------| Read employees by status |------------------------------------------------------
//	@GetMapping("/getEmployeesByStatus/{status}")
//	public ResponseEntity<?> getEmployeesByStatus(@PathVariable("status") String Status) {
//		try {
//			return ResponseEntity.status(HttpStatus.OK).body(this.employeeService.getEmployeeByStatus(Status));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Something went wrong in server side !!!!!");
//		}
//	}
//	
//	// ------------------------------------------------------| Read employees by status |------------------------------------------------------
//	@GetMapping("/getEmployees")
//	public ResponseEntity<?> getEmployees() {
//		try {
//			return ResponseEntity.status(HttpStatus.OK).body(this.employeeService.getUserEmployees("ROLE_USER"));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//				.body("Something went wrong in server side !!!!!");
//		}
//	}

//	// ------------------------------------------------------| Add project |------------------------------------------------------
//	@PostMapping("/addProject")
//	public ResponseEntity<?> addProject(@RequestBody Project project) {
//		try {
//			return ResponseEntity.status(HttpStatus.CREATED).body(this.projectService.addProject(project));
//
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Enter correct details !!");
//		}
//	}
//
//	// ------------------------------------------------------| Update a project |------------------------------------------------------
//	@PatchMapping("/updateProject")
//	public ResponseEntity<?> updateProject(@RequestBody Project project) {
//		try {
//			return ResponseEntity.status(HttpStatus.OK).body(this.projectService.updateProject(project));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body(e.getMessage());
//		}
//	}
//	
//	// ------------------------------------------------------| Read all projects |------------------------------------------------------
//	@GetMapping("/readAllProjects")
//	public ResponseEntity<?> readAllProjects() {
//		try {
//			return ResponseEntity.status(HttpStatus.OK).body(this.projectService.readProjects());
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Something went wrong in server side !!!!!");
//		}
//	}
//	
//	// ------------------------------------------------------| Read all projects |------------------------------------------------------
//	@GetMapping("/readProject/{projectId}")
//	public ResponseEntity<?> readProjectById(@PathVariable("projectId") int projectId) {
//		try {
//			return ResponseEntity.status(HttpStatus.OK).body(this.projectService.readProjectById(projectId));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Something went wrong in server side !!!!!");
//		}
//	}
//	
//	// ------------------------------------------------------| Assign a project to Employee |------------------------------------------------------
//	@PostMapping("/assignProject")
//	public ResponseEntity<?> assignProjectToEmployee(@RequestBody AssignProject assignProject) {
//		try {
//			Employee employee = this.employeeService.readEmployeeById(assignProject.getEmpId());
//			Project project = this.projectService.readProjectById(assignProject.getProjectId());
//			employee.setStatus("Active");
//			employee.setProject(project);
//			this.employeeService.updateEmployee(employee);
//			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully assigned project");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Enter proper informations");
//		}
//	}
	
//	// ------------------------------------------------------| Add a client |------------------------------------------------------
//	@PostMapping("/addClient")
//	public ResponseEntity<?> addClient(@RequestBody Client client) { 
//		try {
//			return ResponseEntity.status(HttpStatus.CREATED).body(this.clientService.addClient(client));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Something went wrong in the server !!");
//		}
//	}
//	
//	// ------------------------------------------------------| Update a client |------------------------------------------------------
//	@PatchMapping("/updateClient")
//	public ResponseEntity<?> updateClient(@RequestBody Client client) {
//		try {
//			return ResponseEntity.status(HttpStatus.OK).body(this.clientService.updateClient(client));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body(e.getMessage());
//		}
//	}
//	
//	// ------------------------------------------------------| Read all clients |------------------------------------------------------
//	@GetMapping("/readAllClients")
//	public ResponseEntity<?> readAllClients() {
//		try {
//			return ResponseEntity.status(HttpStatus.OK).body(this.clientService.readClients());
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Something went wrong in server side !!!!!");
//		}
//	}
//	
//	// ------------------------------------------------------| Assign a project to Client |------------------------------------------------------
//	@PostMapping("/assignClient")
//	public ResponseEntity<?> assignProjectToClient(@RequestBody AssignClient assignClient) {
//		try {
//			Client client = this.clientService.readClientById(assignClient.getClientId());
//			Project project = this.projectService.readProjectById(assignClient.getProjectId());
//			project.setClient(client);
//			client.getProject().add(project);
//			System.out.println(project.getClient().getClientName());
//			this.clientService.updateClient(client);
//			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully assigned project");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Enter proper informations");
//		}
//	}
//	
//	// ------------------------------------------------------| Add a holiday |------------------------------------------------------
//	@PostMapping("/addHoliday")
//	public ResponseEntity<?> addHoliday(@RequestBody Holiday holiday) {
//		try {
//			return ResponseEntity.status(HttpStatus.CREATED).body(this.holidayService.addHoliday(holiday));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Something went wrong in the server !!");
//		}
//	}
//	
//	// ------------------------------------------------------| Update a holiday |------------------------------------------------------
//	@PatchMapping("/updateHoliday")
//	public ResponseEntity<?> updateHoliday(@RequestBody Holiday holiday) {
//		try {
//			return ResponseEntity.status(HttpStatus.OK).body(this.holidayService.updateHoliday(holiday));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body(e.getMessage());
//		}
//	}
//	
//	// ------------------------------------------------------| Read all holidays |------------------------------------------------------
//	@GetMapping("/readAllHolidays")
//	public ResponseEntity<?> readAllHolidays() {
//		try {
//			return ResponseEntity.status(HttpStatus.OK).body(this.holidayService.readAllHolidays());
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Something went wrong in server side !!!!!");
//		}
//	}
	
	// ------------------------------------------------------| Read timeSheet by year |------------------------------------------------------
//	@GetMapping("/readGraphYear/{projectId}")
//	public ResponseEntity<?> readGraphByYear(@PathVariable("projectId") int projectId) {
//		try {
//			return ResponseEntity.status(HttpStatus.OK).body(this.timeSheetRepo.getWokingHourByYear(projectId));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Something went wrong in server side !!!!!");
//		}
//	}
	 
	// ------------------------------------------------------| Read timeSheet by month |------------------------------------------------------
//	@GetMapping("/readGraphMonth/{projectId}/{year}")
//	public ResponseEntity<?> readGraphByMonth(@PathVariable("projectId") int projectId,@PathVariable("year") int year) {
//		try {
//			return ResponseEntity.status(HttpStatus.OK).body(this.timeSheetRepo.getWokingHourByMonth(projectId, year));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Something went wrong in server side !!!!!");
//		}
//	}
	
	// ------------------------------------------------------| Read timeSheet by month |------------------------------------------------------
//	@GetMapping("/readGraphWeek/{projectId}/{year}")
//	public ResponseEntity<?> readGraphByWeek(@PathVariable("projectId") int projectId,@PathVariable("year") int year) {
//		try {
//			return ResponseEntity.status(HttpStatus.OK).body(this.timeSheetRepo.getWokingHourByWeek(projectId, year));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Something went wrong in server side !!!!!");
//		}
//	}
	
	// ------------------------------------------------------| New queries |------------------------------------------------------

	@GetMapping("/getWorkingHourOfYear")
	public ResponseEntity<?> getWorkingHourOfYear(@RequestParam int projectId) {
		// Create ObjectMapper object.
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.enable(SerializationFeature.INDENT_OUTPUT);

//		int pId = workingHour.getProjectId();
		try {
			Project project = this.projectService.readProjectById(projectId);
			int projectIdToPass = project.getProjectId();
			List<Object[]> result = this.timeSheetRepo.getWokingHourByYear(projectIdToPass);
//			String json = mapper.writeValueAsString(result);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.ofNullable(null);
		}

	}

	@GetMapping("/getWorkingHourOfMonth")
	public ResponseEntity<?> getWorkingHourOfMonth(@RequestParam int projectId, @RequestParam int year) {
		try {
			Project project = this.projectService.readProjectById(projectId);
			int projectIdToPass = project.getProjectId();
			List<Object[]> result = this.timeSheetRepo.getWokingHourByMonth(projectIdToPass, year);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.ofNullable("No Woking Hour");
		}
	}

	@GetMapping("/getWorkingHourOfWeek")
	public ResponseEntity<?> getWorkingHourOfWeek(@RequestParam int projectId, @RequestParam int year) {
		try {
			Project project = this.projectService.readProjectById(projectId);
			int projectIdToPass = project.getProjectId();
			List<Object[]> result = this.timeSheetRepo.getWokingHourByWeek(projectIdToPass, year);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.ofNullable("No Woking Hour");
		}
	}

	// Checking Employee filled Timesheet of current week or not
	@GetMapping("/getPreviousTimesheet")
	public ResponseEntity<?> getWeekOfTSFilledByEmployee(@RequestParam int employeeId, @RequestParam int week) {
		List<Object[]> result = this.timeSheetRepo.getWeekOfTSFilledByEMployee(employeeId, week);
		System.out.println(result);
		return ResponseEntity.ok(result);

	}

	// for timesheet filling
	@GetMapping("/getPreviousTimesheetFillDate")
	public ResponseEntity<?> getTimesheetFillDateOfWeek(@RequestParam int eid) {
		try {
			List<Object[]> result = this.timeSheetRepo.getTimesheetPreviousFilledDate(eid);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.ofNullable("No Holiday");
		}
	}
	
	

	@GetMapping("/getHolidayOfYear")
	public ResponseEntity<?> getHolidayOfYear(@RequestParam int year) {
		try {
			List<Object[]> result = this.holidayRepo.getHolidayOfYearWise(year);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.ofNullable("No Holiday");
		}

	}
	
	@GetMapping("/getHolidayOfAllYear")
	public ResponseEntity<?> getHolidayOfAllYear() {
		try {
			List<Object[]> result = this.holidayRepo.getHolidayOfAllOverYears();
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.ofNullable("No Holiday");
		}

	}
	
	@GetMapping("/getHolidayOfProjectYear")
	public ResponseEntity<?> getHolidayOfProjectYear(@RequestParam int startYear,@RequestParam int endYear) {
		try {
			List<Object[]> result = this.holidayRepo.getHolidayOfProjectYears(startYear,endYear);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.ofNullable("No Holiday");
		}
		
	}

	@GetMapping("/getHolidayOfMonth")
	public ResponseEntity<?> getHolidayOfMonth(@RequestParam int year) {
		
		try {
			List<Object[]> result = this.holidayRepo.getHolidayOfMonthOfYear(year);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			
			return ResponseEntity.ofNullable("No Holiday");
		}
	}

	@GetMapping("/getHolidayOfWeek")
	public ResponseEntity<?> getHolidayOfWeek(@RequestParam int year) {
		System.out.print("Hello  ");
		try {
			List<Object[]> result = this.holidayRepo.getHolidayOfWeekOfYear(year);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			System.out.print("No holiday");
			return ResponseEntity.ofNullable("No Holiday  ");
		}
	}
	
	//Get Holiday By Date 
	@GetMapping("/getHolidayByDate")
	public ResponseEntity<?> getHolidayOfWeekTS(@RequestParam int week,@RequestParam int year) {
		try {
			List<Object[]> result = this.holidayRepo.getHolidayByDate(week,year);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.ofNullable("No Holiday");
		}
	}
	
}
