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

import com.pro.api.entities.Holiday;
import com.pro.api.service.HolidayService;

@CrossOrigin
@RestController
@RequestMapping("/holiday")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class HolidayController 
{
	@Autowired
	private HolidayService holidayService;
	
	// ------------------------------------------------------| Add a holiday |------------------------------------------------------
		@PostMapping("/addHoliday")
		public ResponseEntity<?> addHoliday(@RequestBody Holiday holiday) {
			try {
				return ResponseEntity.status(HttpStatus.CREATED).body(this.holidayService.addHoliday(holiday));
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Something went wrong in the server !!");
			}
		}
		
		// ------------------------------------------------------| Update a holiday |------------------------------------------------------
		@PatchMapping("/updateHoliday")
		public ResponseEntity<?> updateHoliday(@RequestBody Holiday holiday) {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(this.holidayService.updateHoliday(holiday));
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(e.getMessage());
			}
		}
		
		// ------------------------------------------------------| Read all holidays |------------------------------------------------------
		@GetMapping("/readAllHolidays")
		public ResponseEntity<?> readAllHolidays() {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(this.holidayService.readAllHolidays());
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Something went wrong in server side !!!!!");
			}
		}
}
