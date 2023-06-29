package com.algaworks.algafood.api.DTO;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomOutput {

	private Integer code;
	
	private BigDecimal diaria;

	private String descricao;
	
	private Boolean disponivel= true;
	
	private HotelOutputId hotel;
}
