package com.pro.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.api.entities.Holiday;

public interface HolidayRepo extends JpaRepository<Holiday, Integer> 
{

}
