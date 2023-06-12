package com.pro.api.service;

import java.util.List;

import com.pro.api.entities.TimeSheet;

public interface TimeSheetService 
{
	// Create
	TimeSheet addTimeSheet(TimeSheet timeSheet);
	
	// Read
	List<TimeSheet> readTimeSheet();
	TimeSheet readTimeSheetById(int timeSheetId);
	
	// Update 
	TimeSheet updateTimeSheet(TimeSheet timeSheet);
}
