package com.algaworks.algafood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.DTO.input.RoomInput;
import com.algaworks.algafood.api.DTO.output.RoomOutput;
import com.algaworks.algafood.domain.model.Room;

@Component
public class RoomMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Room toDomainModel(RoomInput roomInput) {
		return modelMapper.map(roomInput, Room.class);
	}
	
	public List<Room> toCollectionModel(Collection<RoomInput> list) {
		return list.stream().map((models) -> toDomainModel(models)).collect(Collectors.toList());
	}

	//--------------------------------------------------------------------
	public RoomOutput toOutputModel(Room room) {
		return modelMapper.map(room, RoomOutput.class);
	}
	
	public List<RoomOutput> toCollectionOuputModel (Collection<Room> list){
		return list.stream().map((modesl)-> toOutputModel(modesl)).collect(Collectors.toList());
	}
	
	//--------------------------------------------------------------------
	public void copyToDomain(RoomInput RoomInput, Room room) {	 
		modelMapper.map(RoomInput, room);
	}
}
	

