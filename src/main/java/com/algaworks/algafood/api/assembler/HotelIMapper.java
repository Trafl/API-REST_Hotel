package com.algaworks.algafood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.DTO.HotelInput;
import com.algaworks.algafood.api.DTO.HotelOneOutput;
import com.algaworks.algafood.api.DTO.HotelOutput;
import com.algaworks.algafood.domain.model.Hotel;

@Component
public class HotelIMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Hotel toDomainModel(HotelInput hotelInput) {
		return modelMapper.map(hotelInput, Hotel.class);
	}
	
	public List<Hotel> toCollectionModel(List<HotelInput> list) {
		return list.stream().map((models) -> toDomainModel(models)).collect(Collectors.toList());
	}

	//---------------------------------------------------------
	public HotelOutput toOutputModel(Hotel hotel) {
		return modelMapper.map(hotel, HotelOutput.class);
	}
	
	public HotelOneOutput toOneOutputModel(Hotel hotel) {
		return modelMapper.map(hotel, HotelOneOutput.class);
	}
	
	public List<HotelOutput> toCollectionInputModel (Collection<Hotel> list){
		return list.stream().map((modesl)-> toOutputModel(modesl)).collect(Collectors.toList());
	}
	
	//----------------------------------------------------------
	public void copyToDomain(HotelInput hotelInput, Hotel hotel) {	 
		modelMapper.map(hotelInput,hotel);
	}
}
	

