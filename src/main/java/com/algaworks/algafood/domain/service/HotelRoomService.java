package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.RoomFromHotelNotFoundException;
import com.algaworks.algafood.domain.model.Hotel;
import com.algaworks.algafood.domain.model.Room;
import com.algaworks.algafood.domain.model.StatusType;

import jakarta.transaction.Transactional;

@Service
public class HotelRoomService {

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RoomService roomService;
	
	public List<Room> findRooms(Long hotelId){
		List<Room> listOfRooms = hotelService.findOne(hotelId).getQuartos();
		return listOfRooms;
	}
	
	public List<Room> findAvailableRoom(Long hotelId){
		List<Room> listOfRooms = hotelService.findOne(hotelId).getQuartos();
		List<Room> availableRooms = listOfRooms.stream().filter(room -> room.getStatus().equals(StatusType.DISPONIVEL)).toList();
		
		return availableRooms;
	}
	
	@Transactional
	public Room addRoomHotel(Long hotelId, Room room) {
		Hotel hotel = hotelService.findOne(hotelId);		
		
		hotel.getQuartos().add(room);
		room.setHotel(hotel);
		
		roomService.add(room);
		return room;
	}
	
	public Room findOneRoomFromHotel(Long hotelId, Long roomId) {
		return roomService.findOneRoomFromHotelById(hotelId, roomId).orElseThrow(
			 () -> new RoomFromHotelNotFoundException(roomId, hotelId));
	}

	public Room upDateRoom(Room room) {
		return roomService.add(room);
	}
	
	public void deleteRoom(Long roomId) {
		roomService.delete(roomId);
	}
}
