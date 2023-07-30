package com.algaworks.algafood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.DTO.input.RentInput;
import com.algaworks.algafood.api.DTO.output.RentOutput;
import com.algaworks.algafood.domain.model.Rent;

@Component
public class RentMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Rent toDomainModel(RentInput roomInput) {
		return modelMapper.map(roomInput, Rent.class);
	}
	
	public List<Rent> toCollectionModel(Collection<RentInput> list) {
		return list.stream().map((models) -> toDomainModel(models)).collect(Collectors.toList());
	}

	//--------------------------------------------------------------------
	public RentOutput toOutputModel(Rent room) {
		return modelMapper.map(room, RentOutput.class);
	}
	
	public List<RentOutput> toCollectionOuputModel (Collection<Rent> list){
		return list.stream().map((modesl)-> toOutputModel(modesl)).collect(Collectors.toList());
	}
}
	

