package com.algaworks.algafood.api.DTO.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.algafood.domain.model.FormPayment;
import com.algaworks.algafood.domain.model.StatusType;
import com.fasterxml.jackson.annotation.JsonInclude;

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
