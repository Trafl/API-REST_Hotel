package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.DTO.jsonview.OutPutView;
import com.algaworks.algafood.api.DTO.output.RoomOutput;
import com.algaworks.algafood.api.assembler.RoomMapper;
import com.algaworks.algafood.domain.model.Room;
import com.algaworks.algafood.domain.service.HotelRoomService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "hotel/{hotelId}/quarto")
public class HotelRoomController {

	@Autowired
	private HotelRoomService hotelRoomService;
	
	@Autowired 
	private RoomMapper roomMapper;
	
	@GetMapping()
	@JsonView(OutPutView.RoomView.class)
	public List<RoomOutput> findRoomsOfHotel(@PathVariable Long hotelId, @RequestParam(required = false) boolean alugados ) {
		List<Room> list = null;
		
		if(alugados) {
			list = hotelRoomService.findRooms(hotelId);
		}else
			list = hotelRoomService.findAvailableRoom(hotelId);

		return roomMapper.toCollectionOuputModel(list);
	}

	@GetMapping(value = "/{roomId}")
	@JsonView(OutPutView.RoomView.class)
	public RoomOutput findOneRoomOfHotel(@PathVariable Long hotelId ,@PathVariable Long roomId) {
		return roomMapper.toOutputModel(hotelRoomService.findOneRoomFromHotel(hotelId, roomId));
	}
	
	@PutMapping(value = "/{roomId}")
	@ResponseStatus(HttpStatus.OK)
	public void availableRoom(@PathVariable Long hotelId ,@PathVariable Long roomId) {
		hotelRoomService.availableRoom(hotelId, roomId);
	}
	
	@DeleteMapping(value = "/{roomId}")
	@ResponseStatus(HttpStatus.OK)
	public void rentRoom(@PathVariable Long hotelId ,@PathVariable Long roomId) {
		 hotelRoomService.rentRoom(hotelId, roomId);
	}
}  
