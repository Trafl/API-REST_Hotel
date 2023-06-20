package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {
	
	@Column(name = "cep")
	private String cep;
	
	@Column(name = "numero")
	private String number;

	@Column(name = "complemento")
	private String complement;

	@Column(name = "bairro")
	private String neighborhood;
	
	@Column(name = "cidade")
	private String city;
}
