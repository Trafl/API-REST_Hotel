package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Component
public class RentRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private OffsetDateTime checkIn; 
	
	private OffsetDateTime checkOut; 
	
	private BigDecimal valor;
	
	private String observacoes;
	
	@Enumerated(EnumType.STRING)
	private StatusType status;
	
	@OneToOne
	private Client cliente;
	
	@OneToOne()
	private Room quarto;

	public void setRent() {
		Long diferença = ChronoUnit.DAYS.between(checkIn, checkOut);
		BigDecimal result = quarto.getDiaria().multiply(BigDecimal.valueOf(diferença));
		setValor(result);
		quarto.toHire();
		setStatus(StatusType.RESERVADO);
	}
	
	public void liberarQuarto() {
		if(OffsetDateTime.now().isAfter(checkOut)) {
			quarto.toAvailable();
			setStatus(StatusType.FECHADO);
		}
	}
}

