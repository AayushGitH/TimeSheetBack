package com.pro.api.service;

import java.util.List;

import com.pro.api.entities.Project;

public interface ProjectService 
{
	// Create
	Project addProject(Project project);
	
	// Read
	Project readProjectById(int id);
	List<Project> readProjects();
	
	// Update
	Project updateProject(Project project);
}
