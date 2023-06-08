package com.pro.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.api.entities.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>
{
	Employee findByemail(String email);
	List<Employee> findByRole(String role);
	List<Employee> findByStatus(String status);
}
