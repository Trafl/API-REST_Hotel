package com.pivo.hotelo.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pivo.hotelo.domain.exception.EntityInUseException;
import com.pivo.hotelo.domain.exception.RoomNotFoundException;
import com.pivo.hotelo.domain.model.Room;
import com.pivo.hotelo.domain.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;
		
	@Transactional
	public Room add(Room room) {
		return roomRepository.save(room);
	}
	
	public List<Room> findAll() {
		return roomRepository.findAll();
	}
	
	public Room findOne(Long roomId) {
		return roomRepository.findById(roomId).orElseThrow(
				()-> new RoomNotFoundException(roomId));
	}
	
	public Optional<Room> findOneRoomFromHotelById(Long hotelId, Long roomId) {
		return roomRepository.findOneRoomFromHotelById(hotelId, roomId);
	}
	
	@Transactional
	public void delete (Long roomId) {
		try {
			findOne(roomId);
			roomRepository.deleteById(roomId);
			roomRepository.flush();
		}
		catch(DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Quarto de codigo %s não pode ser deletado pois esta em uso", roomId));
		}			
	}
}
