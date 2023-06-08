package com.pro.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.api.entities.Client;

public interface ClientRepo extends JpaRepository<Client, Integer> 
{

}
