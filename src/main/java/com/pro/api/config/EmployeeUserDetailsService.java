package com.pro.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.pro.api.entities.Employee;
import com.pro.api.repo.EmployeeRepo;

@Component
public class EmployeeUserDetailsService implements UserDetailsService 
{
	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Employee employee =  this.employeeRepo.findByemail(userEmail);
		if(employee == null)
		{
			throw new UsernameNotFoundException("Could not found employee !!");
		}
		CustomUserInfo customUserInfo = new CustomUserInfo(employee);
		return customUserInfo;
	}

}
