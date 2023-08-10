package com.pivo.hotelo.api.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pivo.hotelo.api.DTO.input.RoomInput;
import com.pivo.hotelo.api.DTO.output.RoomOutput;
import com.pivo.hotelo.api.assembler.RoomMapper;
import com.pivo.hotelo.api.controller.openapimodel.HotelRoomControllerOpenApi;
import com.pivo.hotelo.domain.model.Room;
import com.pivo.hotelo.domain.service.HotelRoomService;

import jakarta.validation.Valid;

@CrossOrigin(methods = RequestMethod.GET)
@RestController
@RequestMapping(value = "hotel/{hotelId}/quarto")
public class HotelRoomController implements HotelRoomControllerOpenApi {
		
	@Autowired
	private HotelRoomService hotelRoomService;
	
	@Autowired 
	private RoomMapper roomMapper;
	
	@GetMapping()
	public ResponseEntity<CollectionModel<RoomOutput>> findRoomsOfHotel(@PathVariable Long hotelId, @RequestParam(required = false) boolean alugados ) {
		List<Room> list = null;
		
		if(alugados) {
			list = hotelRoomService.findRooms(hotelId);
		}else
			list = hotelRoomService.findAvailableRoom(hotelId);

		CollectionModel<RoomOutput> listOut = roomMapper.toCollectionModel(list);
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(20, TimeUnit.SECONDS))
				.body(listOut);
	}

	@GetMapping(value = "/{roomId}")
	public ResponseEntity<RoomOutput> findOneRoomOfHotel(@PathVariable Long hotelId ,@PathVariable Long roomId) {
		
		RoomOutput room = roomMapper.toModel(hotelRoomService.findOneRoomFromHotel(hotelId, roomId));
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(20, TimeUnit.SECONDS))
				.body(room);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RoomOutput addRoom(@PathVariable Long hotelId,@Valid @RequestBody RoomInput roomInput){
		Room room = roomMapper.toDomainModel(roomInput);
		hotelRoomService.addRoomHotel(hotelId, room);
		return roomMapper.toModel(room);
	}
	@PutMapping(value = "/{roomId}")
	public RoomOutput updateRoom(@PathVariable Long hotelId, @PathVariable Long roomId, @Valid @RequestBody RoomInput roomInput) {
		Room room = hotelRoomService.findOneRoomFromHotel(hotelId, roomId);
		
		roomMapper.copyToDomain(roomInput, room);
		hotelRoomService.upDateRoom(room);
		return roomMapper.toModel(room);
	}
	
	@DeleteMapping(value = "/{roomId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRoom(@PathVariable Long roomId) {
		hotelRoomService.deleteRoom(roomId);
	}
}  
