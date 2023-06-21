package com.algaworks.algafood.api.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressOutput {

	private String cep;
	
	private String numero;

	private String complemento;

	private String bairro;
	
	private String cidade;
	
}
