package com.algaworks.algafood.api.DTO.input;

import java.time.OffsetDateTime;

import com.algaworks.algafood.domain.model.FormPayment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentInput {

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
	
	@NotNull
	private FormPayment pagamento;
}
