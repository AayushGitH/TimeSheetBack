package com.pro.api.service;

import java.util.List;

import com.pro.api.entities.Client;

public interface ClientService 
{
	// Create
	Client addClient(Client client);
	
	// Read 
	List<Client> readClients();
	Client readClientById(int id);
	
	// Update 
	Client updateClient(Client client);
}
