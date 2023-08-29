package com.pivo.hotelo.api.DTO.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "clientes")
@Getter
@Setter
public class ClientOutput extends RepresentationModel<ClientOutput> {
	
	@Schema(example = "Alex") 
	private String nome;
	
	@Schema(example = "(00)000000000") 
	private String celular;
	
	@Schema(example = "000.000.000-00") 
	private String cpf;
	
	@Schema(example = "00.000.000-0") 
	private String rg;	
	
	private UserOutput usuario;
}
