package com.pro.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pro.api.entities.Employee;
import com.pro.api.entities.Holiday;
import com.pro.api.exceptions.ResourceNotFoundException;
import com.pro.api.repo.EmployeeRepo;
import com.pro.api.repo.HolidayRepo;
import com.pro.api.service.EmployeeService;
import com.pro.api.service.HolidayService;

@Service
public class HolidayServiceImpl implements HolidayService 
{
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private HolidayRepo holidayRepo;
	
	// Create
	@Override
	public Holiday addHoliday(Holiday holiday) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Employee user = employeeRepo.findByemail(email);
		holiday.setEmployee(user);
		return this.holidayRepo.save(holiday);
	}

	// Read
	@Override
	public List<Holiday> readAllHolidays() {
		return this.holidayRepo.findAll();
	}

	// Update
	@Override
	public Holiday updateHoliday(Holiday holiday) {
		Holiday uholiday = this.holidayRepo.findById(holiday.getHolidayId()).orElseThrow(() -> new ResourceNotFoundException("Holiday", "Id", holiday.getHolidayId()));
		holiday.setEmployee(uholiday.getEmployee());
		return this.holidayRepo.save(holiday);
	}

}
