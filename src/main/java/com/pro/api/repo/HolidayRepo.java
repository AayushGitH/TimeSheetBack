package com.pro.api.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pro.api.entities.Holiday;

public interface HolidayRepo extends JpaRepository<Holiday, Integer> {
	boolean existsByDate(Date date);

	// Get Holiday of year
	@Query(value = "select count(holiday_date) as noOfHoliday,year(holiday_date) as yearName from holiday group by yearName;", nativeQuery = true)
	List<Object[]> getHolidayOfAllOverYears();

	// Get Holiday of year
	@Query(value = "select count(holiday_date) as noOfHoliday,year(holiday_date) as yearName from holiday where YEAR(holiday_date)>=:startYear and YEAR(holiday_date)<=:endYear group by yearName;", nativeQuery = true)
	List<Object[]> getHolidayOfProjectYears(@Param("startYear") int startYear, @Param("endYear") int endYear);

	// Get Holiday of year
	@Query(value = "SELECT COUNT(holiday_date) as noOfHoliday ,YEAR(holiday_date) as yearName FROM holiday WHERE YEAR(holiday_date) =:yearArg GROUP BY YEAR(holiday_date);", nativeQuery = true)
	List<Object[]> getHolidayOfYearWise(@Param("yearArg") int year);

	// Get Holidays of month of a year
	@Query(value = "SELECT COUNT(holiday_date) as noOfHoliday ,MONTH(holiday_date) as monthName FROM holiday WHERE YEAR(holiday_date) =:yearArg GROUP BY MONTH(holiday_date);", nativeQuery = true)
	List<Object[]> getHolidayOfMonthOfYear(@Param("yearArg") int year);

	// Get Holidays of weeks of a year
	@Query(value = "SELECT COUNT(holiday_date) as noOfHoliday , WEEK(holiday_date) as weekName FROM holiday WHERE YEAR(holiday_date)=:yearArg GROUP BY WEEK(holiday_date)", nativeQuery = true)
	List<Object[]> getHolidayOfWeekOfYear(@Param("yearArg") int year);

	// Get Holidays by date
	@Query(value = " SELECT DAYNAME(holiday_date) from holiday where WEEK(holiday_date)=:weekArg and YEAR(holiday_date)=:yearArg", nativeQuery = true)
	List<Object[]> getHolidayByDate(@Param("weekArg") int week, @Param("yearArg") int year);
}
