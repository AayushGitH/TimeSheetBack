package com.pro.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.api.entities.TimeSheet;

public interface TimeSheetRepo extends JpaRepository<TimeSheet, Integer> 
{

}
