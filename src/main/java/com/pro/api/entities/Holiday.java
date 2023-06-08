package com.pro.api.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Holiday 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int holidayId;
	
	@Column(name = "holidayDescription",columnDefinition = "varchar(30) default 'Public_Holiday'",nullable = false)
	private String holidayDesp;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "holidayDate",nullable = false,unique = true)
	private Date date;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "emp_id")
	private Employee employee;
	
}
