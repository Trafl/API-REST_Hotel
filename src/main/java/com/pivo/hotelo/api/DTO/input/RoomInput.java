package com.pivo.hotelo.api.DTO.input;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomInput {

	@Schema(example = "2")
	@NotNull
	private Integer code;
	
	@Schema(example = "300.00")
	@PositiveOrZero
	private BigDecimal diaria;

	@Schema(example = "Suite presidencial")
	@NotBlank
	private String descricao;
	
}
