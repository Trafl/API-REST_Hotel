package com.pivo.hotelo.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.pivo.hotelo.api.DTO.input.ClientInput;
import com.pivo.hotelo.api.DTO.output.ClientOutput;
import com.pivo.hotelo.api.controller.ClientController;
import com.pivo.hotelo.api.controller.HotelController;
import com.pivo.hotelo.api.controller.RentController;
import com.pivo.hotelo.domain.model.Client;

@Component
public class ClientMapper extends RepresentationModelAssemblerSupport<Client, ClientOutput> {

	@Autowired
	private ModelMapper modelMapper;
	
	public ClientMapper() {
		super(ClientController.class, ClientOutput.class);
	}

	public Client toDomainModel(ClientInput clientInput) {
		return modelMapper.map(clientInput, Client.class);
	}
	
	public List<Client> toDomainCollectionModel(List<ClientInput> list) {
		return list.stream().map((models) -> toDomainModel(models)).collect(Collectors.toList());
	}

	//---------------------------------------------------------
	public ClientOutput toModel(Client client) {
		ClientOutput clientModel = createModelWithId(client.getId(), client);
		
		modelMapper.map(client, clientModel);
		
		clientModel.add(linkTo(methodOn(RentController.class).findRentsByClient(client.getId())).withRel("reservas"));
		clientModel.add(linkTo(HotelController.class).withRel("hoteis"));
		
		return clientModel;
	}

	@Override
	public CollectionModel<ClientOutput> toCollectionModel(Iterable<? extends Client> entities) {
		return super.toCollectionModel(entities)
				.add(linkTo(ClientController.class).withSelfRel());
	}
	
	//----------------------------------------------------------
	public void copyToDomain(ClientInput clientInput, Client client) {	 
		modelMapper.map(clientInput,client);
	}
}
	

