package com.pivo.hotelo.api.DTO.output;

import java.math.BigDecimal;

import com.pivo.hotelo.domain.model.StatusType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomOneOutput {

	private Integer code;
	
	private BigDecimal diaria;

	private String descricao;
	
	private StatusType status;
	
}
