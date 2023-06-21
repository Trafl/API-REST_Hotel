package com.algaworks.algafood.api.DTO;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddressInput {

	@NotBlank
	private String cep;
	
	@NotBlank
	private String number;

	private String complement;
	
	@NotBlank
	private String neighborhood;
	
	@NotBlank
	private String city;
}
