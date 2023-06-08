package com.pro.api.service;

import java.util.List;

import com.pro.api.entities.Employee;

public interface EmployeeService 
{
	// Create
	public Employee createEmployee(Employee employee);
	
	// Read
	public List<Employee> readAllEmployees();
	public Employee readEmployeeById(int id);
	public Employee readEmployeeByEmail(String email);
	public List<Employee> getUserEmployees(String role);
	public List<Employee> getEmployeeByStatus(String status);

	
	// Update
	public Employee updateEmployee(Employee employee);
	
}
