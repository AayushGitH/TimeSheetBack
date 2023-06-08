package com.pro.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.api.entities.Project;

public interface ProjectRepo extends JpaRepository<Project, Integer> 
{

}
