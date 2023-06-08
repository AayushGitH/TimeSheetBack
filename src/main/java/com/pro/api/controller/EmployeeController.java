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
import com.pro.api.service.ClientService;
import com.pro.api.service.EmployeeService;
import com.pro.api.service.JwtService;
import com.pro.api.service.ProjectService;
import com.pro.api.service.TimeSheetService;

@CrossOrigin
@RestController
@RequestMapping("/employee")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private TimeSheetService timeSheetService;
	
	@Autowired
	private JwtService jwtService;
	
	// ------------------------------------------------------| Demo |------------------------------------------------------
	@GetMapping("/home")
	public String welcome() {
		return "Hello after the successfull login";
	}

	// ------------------------------------------------------| Add TimeSheet |------------------------------------------------------
	@PostMapping("/addTimeSheet/{token}")
	public ResponseEntity<?> addTimeSheet(@RequestBody TimeSheet timeSheet,@PathVariable("token") String token)
	{
		try {
			String email = this.jwtService.extractUsername(token);
			Employee employee = this.employeeService.readEmployeeByEmail(email);
			timeSheet.setEmployee(employee);
			return ResponseEntity.status(HttpStatus.CREATED).body(this.timeSheetService.addTimeSheet(timeSheet));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong !!!!!!!!");	
		}
	}
	
	// ------------------------------------------------------| Update TimeSheet |------------------------------------------------------
	@PatchMapping("/updateTimeSheet")
	public ResponseEntity<?> updateTimeSheet(@RequestBody TimeSheet timeSheet) {
		try {
			TimeSheet utimeSheet = this.timeSheetService.readTimeSheetById(timeSheet.getTimesheetId());
			timeSheet.setEmployee(utimeSheet.getEmployee());
			return ResponseEntity.status(HttpStatus.OK).body(this.timeSheetService.updateTimeSheet(timeSheet));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Something went wrong in server side !!!!!");
		}
	}
}
