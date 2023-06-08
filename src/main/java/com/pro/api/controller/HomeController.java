package com.pro.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pro.api.entities.Employee;
import com.pro.api.model.AuthRequest;
import com.pro.api.repo.EmployeeRepo;
import com.pro.api.service.EmployeeService;
import com.pro.api.service.JwtService;

@CrossOrigin
@RestController
@RequestMapping("/home")
public class HomeController 
{
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	// ------------------------------------------------------| Demonstration |------------------------------------------------------
	@GetMapping("/index")
	public String index()
	{
		return "Just for the demonstration";
	}
	
	// ------------------------------------------------------| Create employee |------------------------------------------------------
	@PostMapping("/save")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee)
	{
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		employee.setStatus("Active");
		employee.setRole("ROLE_USER");
		System.out.println(employee);
		try 
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(this.employeeService.createEmployee(employee));
			// return ResponseEntity.status(HttpStatus.CREATED).body(employee);
		} 
		catch (Exception e) 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong in the server");
		}
	}
	
	@PostMapping("/add/{key}")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee,@PathVariable("key") String key)
	{
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		System.out.println(key);
		employee.setStatus("Active");
		employee.setRole("ROLE_USER");
		System.out.println(employee);
		try 
		{
//			return ResponseEntity.status(HttpStatus.CREATED).body(this.employeeService.createEmployee(employee));
			return ResponseEntity.status(HttpStatus.CREATED).body(employee);
		} 
		catch (Exception e) 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong in the server");
		}
	}
	
	// ------------------------------------------------------| Checking authentication |------------------------------------------------------
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequest authRequest)
	{
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		if(authentication.isAuthenticated())
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.jwtService.generateToken(authRequest.getEmail()));
		}
		else
		{
			throw new UsernameNotFoundException("Invalid user credentials");
		}
	}
	
	// ------------------------------------------------------| Gives logged in user details |------------------------------------------------------
	@PostMapping("/details")
	public ResponseEntity<?> demo(@RequestBody String token)
	{
		System.out.println(token);
		String email = this.jwtService.extractUsername(token);
		System.out.println(email);
		Employee user = this.employeeRepo.findByemail(email);
		System.out.println(user);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
	}
}
