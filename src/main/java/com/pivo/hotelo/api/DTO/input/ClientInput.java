package com.pivo.hotelo.api.DTO.input;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientInput {

	@NotBlank
	private String nome;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String celular;
	
	@CPF
	@NotBlank
	private String cpf;
	
	@NotBlank
	private String rg;	
}
