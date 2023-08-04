package com.pivo.hotelo.api.DTO.output.report;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentReportModel {

	private OffsetDateTime checkIn; 
	
	private OffsetDateTime checkOut; 
	
	private BigDecimal valor;
	
	private ClientReport cliente;
	
	private RoomReport quarto;
}
