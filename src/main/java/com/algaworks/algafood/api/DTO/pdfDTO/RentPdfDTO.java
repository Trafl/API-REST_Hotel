package com.algaworks.algafood.api.DTO.pdfDTO;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class RentPdfDTO {
	
	private String cliente;
	private String quarto;
	private Integer code;
	private Date checkIn;
	private Date checkOut;
	private BigDecimal valor;
}
