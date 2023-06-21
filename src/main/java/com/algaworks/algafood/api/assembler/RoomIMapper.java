package com.algaworks.algafood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.DTO.RoomInput;
import com.algaworks.algafood.api.DTO.RoomOutput;
import com.algaworks.algafood.domain.model.Room;

@Component
public class RoomIMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Room ToDomainModel(RoomInput roomInput) {
		return modelMapper.map(roomInput, Room.class);
	}
	
	public List<Room> ToCollectionModel(Collection<RoomInput> list) {
		return list.stream().map((models) -> ToDomainModel(models)).collect(Collectors.toList());
	}

	//--------------------------------------------------------------------
	public RoomOutput ToOutputModel(Room room) {
		return modelMapper.map(room, RoomOutput.class);
	}
	
	public List<RoomOutput> ToCollectionOuputModel (Collection<Room> list){
		return list.stream().map((modesl)-> ToOutputModel(modesl)).collect(Collectors.toList());
	}
}
	

