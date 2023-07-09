package com.algaworks.algafood.api.DTO.output;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomOneOutput {

	private Integer code;
	
	private BigDecimal diaria;

	private String descricao;
	
	private Boolean disponivel;
	
}
