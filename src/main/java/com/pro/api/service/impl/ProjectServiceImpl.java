package com.pro.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.api.entities.Project;
import com.pro.api.exceptions.ResourceNotFoundException;
import com.pro.api.repo.ProjectRepo;
import com.pro.api.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService 
{
	@Autowired
	private ProjectRepo projectRepo;

	// Add project
	@Override
	public Project addProject(Project project) 
	{
		return this.projectRepo.save(project);
	}

	// Get all projects
	@Override
	public List<Project> readProjects() 
	{
		return this.projectRepo.findAll();
	}

	// Get a particular project
	@Override
	public Project readProjectById(int projectId) 
	{
		return this.projectRepo.findById(projectId).orElseThrow();
	}

	// Update a project
	@Override
	public Project updateProject(Project project) 
	{
		Project uproject = this.projectRepo.findById(project.getProjectId()).orElseThrow(() -> new ResourceNotFoundException("Project", "Id", project.getProjectId()));
		project.setClient(uproject.getClient());
		project.setEmployee(uproject.getEmployee());
		return this.projectRepo.save(project);
	}

}
