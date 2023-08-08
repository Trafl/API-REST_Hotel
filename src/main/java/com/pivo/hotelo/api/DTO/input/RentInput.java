package com.pivo.hotelo.api.DTO.input;

import java.time.OffsetDateTime;

import com.pivo.hotelo.domain.model.FormPayment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentInput {

	@Schema(example = "2023-07-08T10:00:00Z")
	@NotNull
	private OffsetDateTime checkIn; 
	
	@Schema(example = "2023-07-12T10:00:00Z\"")
	@NotNull
	private OffsetDateTime checkOut; 
	
	@Schema(example = "Toalhas extras(Opcional)")
	private String observacoes;
	
	@Valid
	@NotNull
	private ClientInputId cliente;
	
	@Valid
	@NotNull
	private RoomInputId quarto;
	
	@Schema(example = "CARTAO")
	@NotNull
	private FormPayment pagamento;
}
