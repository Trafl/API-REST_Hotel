package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.DTO.input.ClientInput;
import com.algaworks.algafood.api.DTO.output.ClientOutput;
import com.algaworks.algafood.api.assembler.ClientMapper;
import com.algaworks.algafood.domain.model.Client;
import com.algaworks.algafood.domain.service.ClientService;

import jakarta.validation.Valid;

@CrossOrigin(methods = RequestMethod.GET)
@RestController
@RequestMapping(value = "/cliente")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ClientMapper clientMapper;
	
	@GetMapping()
	public ResponseEntity<List<ClientOutput>> findAll() {
		List<ClientOutput> list = clientMapper.toCollectionInputModel(clientService.findAll()); 	
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(20, TimeUnit.SECONDS))
				.body(list);
	}
	
	@GetMapping(value = "/{clientId}")
	public ResponseEntity<ClientOutput> findOne(@PathVariable Long clientId) {
		ClientOutput client = clientMapper.toOutputModel(clientService.findOne(clientId)); 	
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(20, TimeUnit.SECONDS))
				.body(client);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ClientOutput add(@RequestBody @Valid ClientInput clientInput) {
		Client client = clientMapper.toDomainModel(clientInput);
		return  clientMapper.toOutputModel(clientService.add(client));
	}
	
	@PutMapping(value = "/{clientId}")
	public ClientOutput update(@RequestBody @Valid ClientInput clientInput, @PathVariable Long clientId) {	
		Client client = clientService.findOne(clientId);
		clientMapper.copyToDomain(clientInput, client);
		clientService.add(client);
		
		return clientMapper.toOutputModel(client);
	}
	
	
	@DeleteMapping(value = "/{clientId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long clientId) {
		clientService.delete(clientId);
	}
}
