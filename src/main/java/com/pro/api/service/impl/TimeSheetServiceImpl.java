package com.pro.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pro.api.entities.Employee;
import com.pro.api.entities.TimeSheet;
import com.pro.api.repo.EmployeeRepo;
import com.pro.api.repo.TimeSheetRepo;
import com.pro.api.service.TimeSheetService;

@Service
public class TimeSheetServiceImpl implements TimeSheetService 
{
	@Autowired
	private TimeSheetRepo timeSheetRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	// Create TimeSheet
	@Override
	public TimeSheet addTimeSheet(TimeSheet timeSheet) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Employee user = employeeRepo.findByemail(email);
		timeSheet.setEmployee(user);
		return this.timeSheetRepo.save(timeSheet);
	}

	// Read TimeSheet
	@Override
	public List<TimeSheet> readTimeSheet() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Employee user = employeeRepo.findByemail(email);
		return this.timeSheetRepo.findByEmployee_empId(user.getEmpId());
	}
	
	@Override
	public TimeSheet readTimeSheetById(int timeSheetId) {
		return this.timeSheetRepo.findById(timeSheetId).orElseThrow();
	}

	// Update TimeSheet
	@Override
	public TimeSheet updateTimeSheet(TimeSheet timeSheet) {
		TimeSheet utimeSheet = this.timeSheetRepo.findById(timeSheet.getTimesheetId()).orElseThrow();
		timeSheet.setEmployee(utimeSheet.getEmployee());
		return this.timeSheetRepo.save(timeSheet);
	}

	

}
