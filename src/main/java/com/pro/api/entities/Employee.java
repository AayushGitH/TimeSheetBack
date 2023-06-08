package com.pro.api.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Employee 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empId")
	private int empId;
	
	@Column(nullable = false, length = 100)
	private String Name;
	
	@Email
	@Column(nullable = false, unique = true)
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@test.com$")
	private String email;
	
	@Size(min = 6, max = 50) 
	@Column(nullable = false, length = 20)
	private String role;
	
	@Column(nullable = false, length = 20)
	private String status;
	
	@Column(nullable = false)
	private String Password;
	
	@Column(nullable = false,length = 50)
	private String Department;
	
	@Column(nullable = false,length = 20)
	private String Contact;
	
	@Column(length = 100)
	private String Address;
	
	@Column(nullable = false,length = 20)
	private String Gender;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<TimeSheet> timeSheet = new ArrayList<>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Holiday> holidays = new ArrayList<>();
}
