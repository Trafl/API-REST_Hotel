package com.algaworks.algafood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.DTO.HotelInput;
import com.algaworks.algafood.api.DTO.HotelOutput;
import com.algaworks.algafood.domain.model.Hotel;

@Component
public class HotelIMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Hotel ToDomainModel(HotelInput hotelInput) {
		return modelMapper.map(hotelInput, Hotel.class);
	}
	
	public List<Hotel> ToCollectionModel(List<HotelInput> list) {
		return list.stream().map((models) -> ToDomainModel(models)).collect(Collectors.toList());
	}

	public HotelOutput ToOutputModel(Hotel hotel) {
		return modelMapper.map(hotel, HotelOutput.class);
	}
	
	public List<HotelOutput> ToCollectionInputModel (Collection<Hotel> list){
		return list.stream().map((modesl)-> ToOutputModel(modesl)).collect(Collectors.toList());
	}
}
	

