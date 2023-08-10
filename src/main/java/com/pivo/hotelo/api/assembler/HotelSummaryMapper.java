package com.pivo.hotelo.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.pivo.hotelo.api.DTO.input.HotelInput;
import com.pivo.hotelo.api.DTO.output.HotelSummaryOutput;
import com.pivo.hotelo.api.controller.HotelController;
import com.pivo.hotelo.domain.model.Hotel;

@Component
public class HotelSummaryMapper extends RepresentationModelAssemblerSupport<Hotel, HotelSummaryOutput> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public HotelSummaryMapper() {
		super(HotelController.class, HotelSummaryOutput.class);
	}
	
	public Hotel toDomainModel(HotelInput hotelInput) {
		return modelMapper.map(hotelInput, Hotel.class);
	}
	
	public List<Hotel> toDomainCollectionModel(List<HotelInput> list) {
		return list.stream().map((models) -> toDomainModel(models)).collect(Collectors.toList());
	}

	//---------------------------------------------------------

	
	public HotelSummaryOutput toModel(Hotel hotel) {
		HotelSummaryOutput hotelSummaryModel = createModelWithId(hotel.getId(), hotel);
		
		modelMapper.map(hotel, hotelSummaryModel );
		
		return hotelSummaryModel;
	}
	
	@Override
	public CollectionModel<HotelSummaryOutput> toCollectionModel(Iterable<? extends Hotel> entities) {
		return super.toCollectionModel(entities)
				.add(linkTo(HotelController.class).withSelfRel());
	}

	
	//----------------------------------------------------------
	public void copyToDomain(HotelInput hotelInput, Hotel hotel) {	 
		modelMapper.map(hotelInput,hotel);
	}

}
	

