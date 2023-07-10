package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.RentNotFoundException;
import com.algaworks.algafood.domain.model.Client;
import com.algaworks.algafood.domain.model.RentRoom;
import com.algaworks.algafood.domain.model.Room;
import com.algaworks.algafood.domain.repository.RentRoomRepository;

@Service
public class RentRoomService {

	@Autowired
	private RentRoomRepository rentRoomRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private RoomService roomService;
	
	public RentRoom findOne(Long rentId) {
		return rentRoomRepository.findById(rentId).orElseThrow(
				() -> new RentNotFoundException(rentId));
	}
	
	public List<RentRoom> findRentByClient(Long clientId) {
		return rentRoomRepository.findRentByClient(clientId).orElseThrow(
			()-> new RentNotFoundException(
					String.format("Não tem nenhuma reserva registrada para o cliente de código %s", clientId)));
	}
	
	// Metodo Get para retornar a reserva em PDF
	
	public List<RentRoom> findAll() {
		return rentRoomRepository.findAll();
	}

	@Transactional
	public RentRoom add(RentRoom rentRoom) {
		
		Client cliente = clientService.findOne(rentRoom.getCliente().getId());
		Room quarto = roomService.findOne(rentRoom.getQuarto().getId());
		
		rentRoom.setCliente(cliente);
		rentRoom.setQuarto(quarto);
		rentRoom.setRent();		
		
		return rentRoomRepository.save(rentRoom);
	}
}

