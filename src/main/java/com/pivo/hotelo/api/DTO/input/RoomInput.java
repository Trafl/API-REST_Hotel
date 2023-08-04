package com.pivo.hotelo.api.DTO.input;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomInput {

	@NotNull
	private Integer code;
	
	@PositiveOrZero
	private BigDecimal diaria;

	@NotBlank
	private String descricao;
	
}
