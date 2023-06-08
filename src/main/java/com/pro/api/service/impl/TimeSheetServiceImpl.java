package com.pro.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.api.entities.TimeSheet;
import com.pro.api.repo.TimeSheetRepo;
import com.pro.api.service.TimeSheetService;

@Service
public class TimeSheetServiceImpl implements TimeSheetService 
{
	@Autowired
	private TimeSheetRepo timeSheetRepo;
	
	// Create TimeSheet
	@Override
	public TimeSheet addTimeSheet(TimeSheet timeSheet) {
		return this.timeSheetRepo.save(timeSheet);
	}

	// Read TimeSheet
	@Override
	public List<TimeSheet> readTimeSheet(int empId) {
		return null;
	}
	
	@Override
	public TimeSheet readTimeSheetById(int timeSheetId) {
		return this.timeSheetRepo.findById(timeSheetId).orElseThrow();
	}

	// Update TimeSheet
	@Override
	public TimeSheet updateTimeSheet(TimeSheet timeSheet) {
		this.timeSheetRepo.findById(timeSheet.getTimesheetId()).orElseThrow();
		return this.timeSheetRepo.save(timeSheet);
	}

	

}
