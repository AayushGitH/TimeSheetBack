package com.pro.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.api.entities.Client;
import com.pro.api.entities.Project;
import com.pro.api.model.AssignClient;
import com.pro.api.service.ClientService;
import com.pro.api.service.ProjectService;

@CrossOrigin
@RestController
@RequestMapping("/client")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class ClientController 
{
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ProjectService projectService;
	
	// ------------------------------------------------------| Add a client |------------------------------------------------------
		@PostMapping("/addClient")
		public ResponseEntity<?> addClient(@RequestBody Client client) { 
			try {
				return ResponseEntity.status(HttpStatus.CREATED).body(this.clientService.addClient(client));
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Something went wrong in the server !!");
			}
		}
		
		// ------------------------------------------------------| Update a client |------------------------------------------------------
		@PatchMapping("/updateClient")
		public ResponseEntity<?> updateClient(@RequestBody Client client) {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(this.clientService.updateClient(client));
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(e.getMessage());
			}
		}
		
		// ------------------------------------------------------| Read all clients |------------------------------------------------------
		@GetMapping("/readAllClients")
		public ResponseEntity<?> readAllClients() {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(this.clientService.readClients());
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Something went wrong in server side !!!!!");
			}
		}
		
		// ------------------------------------------------------| Assign a project to Client |------------------------------------------------------
		@PostMapping("/assignClient")
		public ResponseEntity<?> assignProjectToClient(@RequestBody AssignClient assignClient) {
			try {
				Client client = this.clientService.readClientById(assignClient.getClientId());
				Project project = this.projectService.readProjectById(assignClient.getProjectId());
				project.setClient(client);
				client.getProject().add(project);
				System.out.println(project.getClient().getClientName());
				this.clientService.updateClient(client);
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully assigned project");
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Enter proper informations");
			}
		}
		
}
