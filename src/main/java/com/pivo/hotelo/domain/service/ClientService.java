package com.pivo.hotelo.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pivo.hotelo.domain.exception.ClientNotFoundException;
import com.pivo.hotelo.domain.exception.EntityInUseException;
import com.pivo.hotelo.domain.exception.UserNotFoundException;
import com.pivo.hotelo.domain.model.Client;
import com.pivo.hotelo.domain.model.User;
import com.pivo.hotelo.domain.repository.ClientRepository;
import com.pivo.hotelo.domain.repository.UserRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Client> findAll() {
		return clientRepository.findAll();
	}
	
	public Client findOne(Long clientId) {
		return clientRepository.findById(clientId).orElseThrow(
				()-> new ClientNotFoundException(clientId));
	}

	@Transactional
	public Client add(Client client) {
		
		User user = userRepository.findById(client.getUsuario().getId()).orElseThrow(
				()-> new UserNotFoundException("Usuario não registrado"));
		
		if(userRepository.findByEmail(user.getEmail()) !=null) {
			throw new EntityInUseException("Email de usuario já esta em uso");
		}
		return clientRepository.save(client);
	}

	@Transactional
	public void delete(Long clientId) {
		try {
			findOne(clientId);
			clientRepository.deleteById(clientId);
			clientRepository.flush();
		}
		catch(DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Cliente de codigo %s não pode ser deletado pois esta em uso", clientId));
		}			
	}
}

