package com.pro.api.service;

import java.util.List;

import com.pro.api.entities.Holiday;

public interface HolidayService 
{
	// Create
	Holiday addHoliday(Holiday holiday);
	
	// Read
	List<Holiday> readAllHolidays();
	
	// Update
	Holiday updateHoliday(Holiday holiday);
}
