package com.algaworks.algafood.api.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelOneOutput {

	private String nome;
	private String descricao;
	private AddressOutput endereco;
	private List<RoomOneOutput> quartos;
}
