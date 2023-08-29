package com.pivo.hotelo.api.DTO.input;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientInput {

	@Schema(example = "Alex")
	@NotBlank
	private String nome;
	
	@Schema(example = "(00)000000000")
	@NotBlank
	private String celular;
	
	@CPF
	@NotBlank
	@Schema(example = "000.000.000-00")
	private String cpf;
	
	@Schema(example = "00.000.000-0")
	@NotBlank
	private String rg;	
	
	@NotNull
	@Schema(example = "1")
	private UserInputId usuario;
}
