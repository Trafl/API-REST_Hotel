package com.algaworks.algafood.api.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelOutput {

	private String nome;
	private String descricao;
	private AddressOutput endereco;
}
