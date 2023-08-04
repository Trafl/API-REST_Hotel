package com.pivo.hotelo.api.DTO.output;

import com.fasterxml.jackson.annotation.JsonView;
import com.pivo.hotelo.api.DTO.jsonview.OutPutView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressOutput {

	@JsonView(OutPutView.HotelView.class)
	private String cep;
	
	@JsonView(OutPutView.HotelView.class)
	private String numero;

	@JsonView(OutPutView.HotelView.class)
	private String complemento;

	@JsonView(OutPutView.HotelView.class)
	private String bairro;
	
	@JsonView(OutPutView.HotelView.class)
	private String cidade;
	
}
