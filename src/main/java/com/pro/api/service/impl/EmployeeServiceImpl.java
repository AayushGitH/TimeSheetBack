package com.pro.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.api.entities.Employee;
import com.pro.api.exceptions.ResourceNotFoundException;
import com.pro.api.repo.EmployeeRepo;
import com.pro.api.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService 
{
	@Autowired
	private EmployeeRepo employeeRepo;

	// Create
	@Override
	public Employee createEmployee(Employee employee) {
		return this.employeeRepo.save(employee);
	}

	// Read
	@Override
	public List<Employee> readAllEmployees() {
		return this.employeeRepo.findAll();
	}

	@Override
	public Employee readEmployeeById(int id) {
		return this.employeeRepo.findById(id).orElseThrow();
	}
	
	@Override
	public List<Employee> getEmployeeByStatus(String status) {
		return this.employeeRepo.findByStatus(status);
	}
	
	@Override
	public Employee readEmployeeByEmail(String email) {
		return this.employeeRepo.findByemail(email);
	}

	// Update
	@Override
	public Employee updateEmployee(Employee employee) {
		Employee uemployee = this.employeeRepo.findById(employee.getEmpId()).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employee.getEmpId()));
		employee.setHolidays(uemployee.getHolidays());
		employee.setProject(uemployee.getProject());
		employee.setTimeSheet(uemployee.getTimeSheet());
		return this.employeeRepo.save(employee);
	}

	// Read employees by status
	@Override
	public List<Employee> getUserEmployees(String role) 
	{
		return this.employeeRepo.findByRole(role);
	}

}
