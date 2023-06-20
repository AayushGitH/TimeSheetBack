package com.pro.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.api.entities.TimeSheet;
import com.pro.api.service.TimeSheetService;

@CrossOrigin
@RestController
@RequestMapping("/timesheet")
@PreAuthorize("hasAuthority('ROLE_USER')")
public class TimeSheetController 
{
	
	@Autowired
	private TimeSheetService timeSheetService;
	
	// ------------------------------------------------------| Demo |------------------------------------------------------
	@GetMapping("/home")
	public String welcome() {
		return "Hello after the successfull login";
	}

	// ------------------------------------------------------| Add TimeSheet |------------------------------------------------------
	@PostMapping("/addTimeSheet")
	public ResponseEntity<?> addTimeSheet(@RequestBody TimeSheet timeSheet)
	{
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.timeSheetService.addTimeSheet(timeSheet));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong !!!!!!!!");	
		}
	}
	
	// ------------------------------------------------------| Update TimeSheet |------------------------------------------------------
	@PatchMapping("/updateTimeSheet")
	public ResponseEntity<?> updateTimeSheet(@RequestBody TimeSheet timeSheet) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.timeSheetService.updateTimeSheet(timeSheet));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
	}
	
	// ------------------------------------------------------| View TimeSheets |------------------------------------------------------
	@GetMapping("/readTimeSheets")
	public ResponseEntity<?> readTimeSheets() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.timeSheetService.readTimeSheet());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Something went wrong in server side !!!!!");
		}
	}
}
