package com.algaworks.algafood.api.DTO.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelInput {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	@Valid
	@NotNull
	private AddressInput endereco;
}
