package com.algaworks.algafood.api.DTO.output;

import java.util.List;

import com.algaworks.algafood.api.DTO.jsonview.OutPutView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelOutput {

	@JsonView(OutPutView.HotelView.class)
	private String nome;
	
	@JsonView(OutPutView.HotelView.class)
	private String descricao;
	
	@JsonView(OutPutView.HotelView.class)
	private AddressOutput endereco;
	
	private List<RoomOneOutput> quartos;
}
