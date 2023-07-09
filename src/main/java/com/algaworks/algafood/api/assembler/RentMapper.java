package com.algaworks.algafood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.DTO.input.RentRoomInput;
import com.algaworks.algafood.api.DTO.output.RentRoomOutput;
import com.algaworks.algafood.domain.model.RentRoom;

@Component
public class RentMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public RentRoom toDomainModel(RentRoomInput roomInput) {
		return modelMapper.map(roomInput, RentRoom.class);
	}
	
	public List<RentRoom> toCollectionModel(Collection<RentRoomInput> list) {
		return list.stream().map((models) -> toDomainModel(models)).collect(Collectors.toList());
	}

	//--------------------------------------------------------------------
	public RentRoomOutput toOutputModel(RentRoom room) {
		return modelMapper.map(room, RentRoomOutput.class);
	}
	
	public List<RentRoomOutput> toCollectionOuputModel (Collection<RentRoom> list){
		return list.stream().map((modesl)-> toOutputModel(modesl)).collect(Collectors.toList());
	}
}
	

