package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.RoomFromHotelNotFoundException;
import com.algaworks.algafood.domain.model.Room;
import com.algaworks.algafood.domain.repository.RoomRepository;

@Service
public class HotelRoomService {

	@Autowired
	HotelService hotelService;
	
	@Autowired
	RoomRepository roomRepository;
	
	public List<Room> findRooms(Long hotelId){
		List<Room> listOfRooms = hotelService.findOne(hotelId).getQuartos();
		return listOfRooms;
	}
	
	public List<Room> findAvailableRoom(Long hotelId){
		List<Room> listOfRooms = hotelService.findOne(hotelId).getQuartos();
		List<Room> availableRooms = listOfRooms.stream().filter(room -> room.getDisponivel().equals(true)).toList();
		
		return availableRooms;
	}
	
	@Transactional
	public void rentRoom(Long hotelId, Long roomId) {
		Room rentRoom = findOneRoomFromHotel(hotelId, roomId);
		rentRoom.toHire();
	}
	
	@Transactional
	public void availableRoom(Long hotelId, Long roomId) {
		Room rentRoom = findOneRoomFromHotel(hotelId, roomId);
		rentRoom.toAvailable();
	}
	
	public Room findOneRoomFromHotel(Long hotelId, Long roomId) {
		return roomRepository.findOneRoomFromHotelById(hotelId, roomId).orElseThrow(
			 () -> new RoomFromHotelNotFoundException(roomId, hotelId));
	}
}
