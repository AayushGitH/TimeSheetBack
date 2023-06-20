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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pro.api.entities.Client;
import com.pro.api.entities.Employee;
import com.pro.api.entities.Project;
import com.pro.api.entities.TimeSheet;
import com.pro.api.model.AssignProject;
import com.pro.api.repo.HolidayRepo;
import com.pro.api.repo.TimeSheetRepo;
import com.pro.api.service.ClientService;
import com.pro.api.service.EmployeeService;
import com.pro.api.service.HolidayService;
import com.pro.api.service.JwtService;
import com.pro.api.service.ProjectService;
import com.pro.api.service.TimeSheetService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/employee")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/first")
	public String index()
	{
		return "First page adsf;afj;a";
	}


	// ------------------------------------------------------| Add employee as a User |------------------------------------------------------
	@PostMapping("/addEmployee")
	public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee) {
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		employee.setStatus("Inactive");
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
	
	// ------------------------------------------------------| Read employees by role |------------------------------------------------------
	@GetMapping("/getEmployees")
	public ResponseEntity<?> getEmployees() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.employeeService.getUserEmployees("ROLE_USER"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Something went wrong in server side !!!!!");
		}
	}
}
