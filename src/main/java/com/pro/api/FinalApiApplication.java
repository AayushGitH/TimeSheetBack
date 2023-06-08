package com.pro.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.annotation.DateTimeFormat;

import com.pro.api.entities.Employee;
import com.pro.api.service.EmployeeService;
	
@SpringBootApplication
public class FinalApiApplication implements CommandLineRunner 
{
	Logger logger = LogManager.getLogger(FinalApiApplication.class);
	@Autowired
	private EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(FinalApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("I am under the run method implementation");
		List<Employee> employees = employeeService.readAllEmployees();
		for(Employee e : employees)
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now();
			if(e.getProject() != null)
			{
				if(dtf.format(now).toString().compareTo(e.getProject().getEndDate().toString())>0 
						|| dtf.format(now).toString().compareTo(e.getProject().getEndDate().toString())==0)
				{
					System.out.println("Date occurs before today date or equals to todays date");
					e.setStatus("Active");
					this.employeeService.updateEmployee(e);
				}
				else
				{
					System.out.println("Date occurs after todays date");
					e.setStatus("Inactive");
					this.employeeService.updateEmployee(e);
				}
			}
		}
	}

}