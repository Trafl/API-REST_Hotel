package com.pivo.hotelo.domain.service;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pivo.hotelo.api.DTO.pdfDTO.RentPdfDTO;
import com.pivo.hotelo.domain.exception.RentNotFoundException;
import com.pivo.hotelo.domain.model.Client;
import com.pivo.hotelo.domain.model.Rent;
import com.pivo.hotelo.domain.model.Room;
import com.pivo.hotelo.domain.repository.RentRepository;

@Service
public class RentService {

	@Autowired
	private RentRepository rentRoomRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private RoomService roomService;
	
	public Rent findOne(Long rentId) {
		return rentRoomRepository.findById(rentId).orElseThrow(
				() -> new RentNotFoundException(rentId));
	}
	
	public List<Rent> findRentByClient(Long clientId) {
		return rentRoomRepository.findRentByClient(clientId).orElseThrow(
			()-> new RentNotFoundException(
					String.format("Não tem nenhuma reserva registrada para o cliente de código %s", clientId)));
	}

	public List<Rent> findAll() {
		return rentRoomRepository.findAll();
	}

	@Transactional
	public Rent add(Rent rentRoom) {
		
		Client cliente = clientService.findOne(rentRoom.getCliente().getId());
		Room quarto = roomService.findOne(rentRoom.getQuarto().getId());
		
		rentRoom.setCliente(cliente);
		rentRoom.setQuarto(quarto);
		rentRoom.setRent();	
		
		rentRoom = rentRoomRepository.save(rentRoom);
		
		return rentRoom;
	}
	
	public RentPdfDTO findRentForPdf(Long rentId) {
		Rent rent = findOne(rentId);
		
		return 	 RentPdfDTO.builder()
				.cliente(rent.getCliente().getNome())
				.quarto(rent.getQuarto().getHotel().getNome())
				.checkIn(convertOffSetForDate(rent.getCheckIn()))
				.checkOut(convertOffSetForDate(rent.getCheckOut()))
				.code(rent.getQuarto().getCode())
				.valor(rent.getValor())
				.build();
	}
	
	public Date convertOffSetForDate(OffsetDateTime time) {
		return Date.from(time.toInstant());
	}
}

