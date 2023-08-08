package com.pivo.hotelo.api.DTO.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddressInput {

	@Schema(example = "22.574-040")
	@NotBlank
	private String cep;
	
	@Schema(example = "245")
	@NotBlank
	private String numero;

	@Schema(example = "Casa")
	private String complemento;
	
	@Schema(example = "Japuiba")
	@NotBlank
	private String bairro;
	
	@Schema(example = "Angra dos reis")
	@NotBlank
	private String cidade;
}
