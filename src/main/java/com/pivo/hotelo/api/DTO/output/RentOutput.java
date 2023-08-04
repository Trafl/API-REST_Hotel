package com.pivo.hotelo.api.DTO.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pivo.hotelo.domain.model.FormPayment;
import com.pivo.hotelo.domain.model.StatusType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RentOutput {

	private OffsetDateTime checkIn; 
	
	private OffsetDateTime checkOut; 
	
	private BigDecimal valor;
	
	private FormPayment pagamento;
	
	private StatusType status;
	
	private String observacoes;
	
	private Long clienteId;
	
	private Long quartoId;
}
