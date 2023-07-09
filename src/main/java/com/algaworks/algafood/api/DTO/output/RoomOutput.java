package com.algaworks.algafood.api.DTO.output;

import java.math.BigDecimal;

import com.algaworks.algafood.api.DTO.jsonview.OutPutView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomOutput {

	@JsonView(OutPutView.RoomView.class)
	private Integer code;
	
	@JsonView(OutPutView.RoomView.class)
	private BigDecimal diaria;

	@JsonView(OutPutView.RoomView.class)
	private String descricao;
	
	@JsonView(OutPutView.RoomView.class)
	private Boolean disponivel;
	
	private HotelOutputId hotel;
}
