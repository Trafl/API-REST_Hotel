package com.pivo.hotelo.api.DTO.output;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.pivo.hotelo.api.DTO.jsonview.OutPutView;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelOutput {

	@Schema(example = "HotelA")
	@JsonView(OutPutView.HotelView.class)
	private String nome;
	
	@Schema(example = "Perto do centro comercial")
	@JsonView(OutPutView.HotelView.class)
	private String descricao;
	
	
	@JsonView(OutPutView.HotelView.class)
	private AddressOutput endereco;
	
	private List<RoomOneOutput> quartos;
}
