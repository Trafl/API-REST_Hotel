package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.DTO.RoomOutput;
import com.algaworks.algafood.api.assembler.RoomIMapper;
import com.algaworks.algafood.domain.model.Hotel;
import com.algaworks.algafood.domain.model.Room;
import com.algaworks.algafood.domain.service.HotelService;

@RestController
@RequestMapping(value = "hotel/{hotelId}/quarto")
public class Hotel_RoomController {

	@Autowired
	private HotelService hotelService;
	
	@Autowired RoomIMapper roomMapper;
	
	@GetMapping()
	public List<RoomOutput> findRoomsOfHotel(@PathVariable Long hotelId) {
		Hotel hotel = hotelService.findOne(hotelId);
		return roomMapper.ToCollectionOuputModel(hotel.getQuartos());
	}

	@GetMapping(value = "/{roomId}")
	public Room findOne(@PathVariable Long hotelId ,@PathVariable Long roomId) {
		return null;
	}
}  
