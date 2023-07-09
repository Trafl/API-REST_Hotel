package com.algaworks.algafood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.DTO.input.ClientInput;
import com.algaworks.algafood.api.DTO.output.ClientOutput;
import com.algaworks.algafood.domain.model.Client;

@Component
public class ClientMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Client toDomainModel(ClientInput clientInput) {
		return modelMapper.map(clientInput, Client.class);
	}
	
	public List<Client> toCollectionModel(List<ClientInput> list) {
		return list.stream().map((models) -> toDomainModel(models)).collect(Collectors.toList());
	}

	//---------------------------------------------------------
	public ClientOutput toOutputModel(Client client) {
		return modelMapper.map(client, ClientOutput.class);
	}
	
	public List<ClientOutput> toCollectionInputModel (Collection<Client> list){
		return list.stream().map((modesl)-> toOutputModel(modesl)).collect(Collectors.toList());
	}
	
	//----------------------------------------------------------
	public void copyToDomain(ClientInput clientInput, Client client) {	 
		modelMapper.map(clientInput,client);
	}
}
	

