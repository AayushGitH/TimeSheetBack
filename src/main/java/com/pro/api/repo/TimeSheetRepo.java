package com.pro.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pro.api.entities.TimeSheet;

public interface TimeSheetRepo extends JpaRepository<TimeSheet, Integer> 
{
	// TimeSheet by Employee id
	List<TimeSheet> findByEmployee_empId(int empId);
	
	// Year
	@Query(value="SELECT YEAR(date) AS Year,SUM(workinghour) AS Hours,project_id FROM time_sheet t GROUP BY YEAR(date) having project_id=:pid",nativeQuery = true)
	List<Object[]> getWokingHourByYear(@Param("pid") int projectId);
	
	// Month
	@Query(value = "SELECT MONTH(date) AS month, YEAR(date) as yearName, SUM(workinghour) as Hours,project_id from time_sheet group by YEAR(date),MONTH(date) having yearName=:yearArg and project_id=:pid",nativeQuery = true)
	List<Object[]> getWokingHourByMonth(@Param("pid") int projectId,@Param("yearArg") int year);
	
	// Week
	@Query(value = "SELECT WEEK(date) AS week, YEAR(date) as yearName ,SUM(workinghour) as Hours,project_id from time_sheet group by YEAR(date),MONTH(date),WEEK(date) having yearName=:yearArg and project_id=:pid",nativeQuery = true)
	List<Object[]> getWokingHourByWeek(@Param("pid") int projectId,@Param("yearArg") int year);
}
