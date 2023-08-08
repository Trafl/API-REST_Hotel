package com.pivo.hotelo.api.DTO.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pivo.hotelo.domain.model.FormPayment;
import com.pivo.hotelo.domain.model.StatusType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RentOutput {

	@Schema(example = "2023-07-08T10:00:00Z")
	private OffsetDateTime checkIn; 
	
	@Schema(example = "2023-07-12T10:00:00Z")
	private OffsetDateTime checkOut; 
	
	@Schema(example = "1328.00")
	private BigDecimal valor;
	
	@Schema(example = "CARTAO")
	private FormPayment pagamento;
	
	@Schema(example = "RESERVADO")
	private StatusType status;
	
	@Schema(example = "Toalhas extras(opcional)")
	private String observacoes;
	
	@Schema(example = "1")
	private Long clienteId;
	
	@Schema(example = "2")
	private Long quartoId;
}
