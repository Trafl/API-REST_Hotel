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

import com.pivo.hotelo.api.DTO.input.RentInput;
import com.pivo.hotelo.api.DTO.output.RentOutput;
import com.pivo.hotelo.api.controller.ClientController;
import com.pivo.hotelo.api.controller.HotelController;
import com.pivo.hotelo.api.controller.HotelRoomController;
import com.pivo.hotelo.api.controller.RentController;
import com.pivo.hotelo.domain.model.Rent;

@Component
public class RentMapper extends RepresentationModelAssemblerSupport<Rent, RentOutput> {

	public RentMapper() {
		super(RentController.class, RentOutput.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	public Rent toDomainModel(RentInput roomInput) {
		return modelMapper.map(roomInput, Rent.class);
	}
	
	public List<Rent> toCollectionDomainModel(Collection<RentInput> list) {
		return list.stream().map((models) -> toDomainModel(models)).collect(Collectors.toList());
	}

	//--------------------------------------------------------------------
	public RentOutput toModel(Rent rent) {
		RentOutput rentModel = createModelWithId(rent.getId(), rent);
		
		modelMapper.map(rent, rentModel);
		
		rentModel.add(linkTo(methodOn(ClientController.class).findOne(rent.getCliente().getId())).withRel("cliente"));
		rentModel.add(linkTo(methodOn(HotelRoomController.class).findOneRoomOfHotel(rent.getQuarto().getHotel().getId(), rent.getQuarto().getId())).withRel("quarto"));
		rentModel.add(linkTo(methodOn(HotelController.class).findOne(rent.getQuarto().getHotel().getId())).withRel("hotel"));
		
		return rentModel;
	}
	
	@Override
	public CollectionModel<RentOutput> toCollectionModel(Iterable<? extends Rent> entities) {
		return super.toCollectionModel(entities);
	}
}
	

