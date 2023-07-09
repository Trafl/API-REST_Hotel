package com.algaworks.algafood.api.DTO.input;

import java.time.OffsetDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentRoomInput {

	@NotNull
	private OffsetDateTime checkIn; 
	
	@NotNull
	private OffsetDateTime checkOut; 
	
	private String observacoes;
	
	@Valid
	@NotNull
	private ClientInputId cliente;
	
	@Valid
	@NotNull
	private RoomInputId quarto;
}
