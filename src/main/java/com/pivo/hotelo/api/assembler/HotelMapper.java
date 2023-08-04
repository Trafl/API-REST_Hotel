package com.pivo.hotelo.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pivo.hotelo.api.DTO.input.HotelInput;
import com.pivo.hotelo.api.DTO.output.HotelOutput;
import com.pivo.hotelo.domain.model.Hotel;

@Component
public class HotelMapper {

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
	
	public List<HotelOutput> toCollectiononeOutputModel (Collection<Hotel> list){
		return list.stream().map((modesl)-> toOutputModel(modesl)).collect(Collectors.toList());
	}

	
	//----------------------------------------------------------
	public void copyToDomain(HotelInput hotelInput, Hotel hotel) {	 
		modelMapper.map(hotelInput,hotel);
	}
}
	

