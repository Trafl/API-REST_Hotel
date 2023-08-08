package com.pivo.hotelo.api.DTO.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelInput {

	@Schema(example = "HotelA")
	@NotBlank
	private String nome;
	
	@Schema(example = "Perto do centro comercial")
	@NotBlank
	private String descricao;
	
	@Valid
	@NotNull
	private AddressInput endereco;
}
