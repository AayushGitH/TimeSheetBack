package com.pro.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.api.entities.Client;
import com.pro.api.exceptions.ResourceNotFoundException;
import com.pro.api.repo.ClientRepo;
import com.pro.api.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService 
{
	@Autowired
	private ClientRepo clientRepo;

	// Add Client
	@Override
	public Client addClient(Client client) 
	{
		return this.clientRepo.save(client);
	}

	// Read Clients
	@Override
	public List<Client> readClients() 
	{
		return this.clientRepo.findAll();
	}

	@Override
	public Client readClientById(int id) {
		return this.clientRepo.findById(id).orElseThrow();
	}

	// Update Client
	@Override
	public Client updateClient(Client client) {
		Client uclient = this.clientRepo.findById(client.getClientId()).orElseThrow(() -> new ResourceNotFoundException("Client", "Id", client.getClientId()));
		client.setProject(uclient.getProject());
		return this.clientRepo.save(client);
	}

}
