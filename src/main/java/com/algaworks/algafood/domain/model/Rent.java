package com.algaworks.algafood.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.event.EmailEvent;

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
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Component
public class Rent extends AbstractAggregateRoot<Rent> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private String fileName = generateFileName();
	
	private OffsetDateTime checkIn; 
	
	private OffsetDateTime checkOut; 
	
	private BigDecimal valor;
	
	private String observacoes;
	
	@Enumerated(EnumType.STRING)
	private StatusType status;
	
	@Enumerated(EnumType.STRING)
	private FormPayment pagamento;
	
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
		registerEvent(new EmailEvent(this));
	}
	
	public void releaseRoom() {
		if(OffsetDateTime.now().isAfter(checkOut)) {
			quarto.toAvailable();
			setStatus(StatusType.FECHADO);
		}
	}
	
	public String generateFileName() {
		return UUID.randomUUID().toString();
	}
}

