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
	@Query(value="SELECT YEAR(date) AS Year,SUM(working_hour) AS Hours,project_id FROM time_sheet t GROUP BY YEAR(date) having project_id=:pid",nativeQuery = true)
	List<Object[]> getWokingHourByYear(@Param("pid") int projectId);
	
	// Month
	@Query(value = "SELECT MONTH(date) AS month, YEAR(date) as yearName, SUM(working_hour) as Hours,project_id from time_sheet group by YEAR(date),MONTH(date) having yearName=:yearArg and project_id=:pid",nativeQuery = true)
	List<Object[]> getWokingHourByMonth(@Param("pid") int projectId,@Param("yearArg") int year);
	
	// Week
	@Query(value = "SELECT WEEK(date) AS week, YEAR(date) as yearName ,SUM(working_hour) as Hours,project_id from time_sheet group by YEAR(date),MONTH(date),WEEK(date) having yearName=:yearArg and project_id=:pid",nativeQuery = true)
	List<Object[]> getWokingHourByWeek(@Param("pid") int projectId,@Param("yearArg") int year);
	
	// Checking Employee filled timesheet of current week or not
	@Query(value = "SELECT WEEK(date),fill_date from time_sheet where emp_id=:eid and WEEK(date)=:weekArg ;", nativeQuery = true)
	List<Object[]> getWeekOfTSFilledByEMployee(@Param("eid") int employeeId, @Param("weekArg") int week);

	// Get Timesheet fill by week of a year
	@Query(value = "SELECT date from time_sheet where emp_id=:empId ORDER BY timesheetid DESC LIMIT 1;", nativeQuery = true)
	List<Object[]> getTimesheetPreviousFilledDate(@Param("empId") int eid);
	
//	@Query(value = "SELECT YEAR(fill_date) AS Year,SUM(workinghour) AS Hours,project_id FROM time_sheet where project_id=:pid GROUP BY YEAR(fill_date);", nativeQuery = true)
//	List<Object[]> getWokingHourByYear(@Param("pid") int projectId);
	
//	@Query(value = "SELECT MONTH(fill_date) AS month, YEAR(fill_date) as yearName ,SUM(workinghour) as Hours,project_id from time_sheet where YEAR(fill_date)=:yearArg and project_id=:pid group by Year(fill_date), MONTH(fill_date);", nativeQuery = true)
//	List<Object[]> getWokingHourByMonth(@Param("pid") int projectId, @Param("yearArg") int year);
}
