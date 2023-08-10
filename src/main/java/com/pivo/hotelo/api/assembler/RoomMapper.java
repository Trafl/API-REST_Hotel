package com.pivo.hotelo.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.pivo.hotelo.api.DTO.input.RoomInput;
import com.pivo.hotelo.api.DTO.output.RoomOutput;
import com.pivo.hotelo.api.controller.HotelRoomController;
import com.pivo.hotelo.api.controller.RentController;
import com.pivo.hotelo.domain.model.Room;

@Component
public class RoomMapper extends RepresentationModelAssemblerSupport<Room, RoomOutput> {

	@Autowired
	private ModelMapper modelMapper;
	
	public RoomMapper() {
		super(HotelRoomController.class, RoomOutput.class);
	}
	
	public Room toDomainModel(RoomInput roomInput) {
		return modelMapper.map(roomInput, Room.class);
	}
	
	public List<Room> toDomainCollectionModel(Collection<RoomInput> list) {
		return list.stream().map((models) -> toDomainModel(models)).collect(Collectors.toList());
	}

	//--------------------------------------------------------------------
	public RoomOutput toModel(Room room) {
	
		RoomOutput roomModel=  modelMapper.map(room, RoomOutput.class);
		roomModel.add(linkTo(methodOn(HotelRoomController.class).findOneRoomOfHotel(room.getHotel().getId(), room.getId())).withSelfRel());
		roomModel.add(linkTo(RentController.class).withRel("reserva"));
		return roomModel;
	}
	
	@Override
	public CollectionModel<RoomOutput> toCollectionModel(Iterable<? extends Room> entities) {
		return super.toCollectionModel(entities);
	}
	
	//--------------------------------------------------------------------
	public void copyToDomain(RoomInput RoomInput, Room room) {	 
		modelMapper.map(RoomInput, room);
	}
}
	

