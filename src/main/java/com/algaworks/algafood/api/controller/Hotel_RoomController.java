package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.DTO.RoomOutput;
import com.algaworks.algafood.api.assembler.RoomIMapper;
import com.algaworks.algafood.domain.model.Room;
import com.algaworks.algafood.domain.service.HotelRoomService;

@RestController
@RequestMapping(value = "hotel/{hotelId}/quarto")
public class Hotel_RoomController {

	@Autowired
	private HotelRoomService hotelRoomService;
	
	@Autowired 
	private RoomIMapper roomMapper;
	
	@GetMapping()
	public List<RoomOutput> findRoomsOfHotel(@PathVariable Long hotelId) {
		List<Room> trueList = hotelRoomService.FindAvailableRoom(hotelId);
		
		return roomMapper.ToCollectionOuputModel(trueList);
	}

	@GetMapping(value = "/{roomId}")
	public RoomOutput FindOneRoomOfHotel(@PathVariable Long hotelId ,@PathVariable Long roomId) {
		return roomMapper.ToOutputModel(hotelRoomService.findOneRoomFromHotel(hotelId, roomId));
	}
	
	@PutMapping(value = "/{roomId}")
	@ResponseStatus(HttpStatus.OK)
	public void AvailableRoom(@PathVariable Long hotelId ,@PathVariable Long roomId) {
		hotelRoomService.availableRoom(hotelId, roomId);
	}
	
	@DeleteMapping(value = "/{roomId}")
	@ResponseStatus(HttpStatus.OK)
	public void RentRoom(@PathVariable Long hotelId ,@PathVariable Long roomId) {
		 hotelRoomService.RentRoom(hotelId, roomId);
	}
}  
