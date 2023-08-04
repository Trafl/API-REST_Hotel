package com.pivo.hotelo.domain.model;

import jakarta.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {
	
	private String cep;
	
	private String numero;

	private String complemento;

	private String bairro;
	
	private String cidade;
}
