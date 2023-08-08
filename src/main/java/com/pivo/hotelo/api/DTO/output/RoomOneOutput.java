package com.pivo.hotelo.api.DTO.output;

import java.math.BigDecimal;

import com.pivo.hotelo.domain.model.StatusType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomOneOutput {

	@Schema(example = "1")
	private Integer code;
	
	@Schema(example = "152.20")
	private BigDecimal diaria;

	@Schema(example = "Quarto bem arejado no terceiro andar de frente a praia")
	private String descricao;
	
	@Schema(example = "DISPONIVEL")
	private StatusType status;
	
}
