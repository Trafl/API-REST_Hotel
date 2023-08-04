package com.pivo.hotelo.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private Integer code;
	
	private BigDecimal diaria;

	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private StatusType status= StatusType.DISPONIVEL;
	
	@ManyToOne()
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;
	
	
	public void toHire() {
		setStatus(StatusType.RESERVADO);
	}
	
	public void toAvailable() {
		setStatus(StatusType.DISPONIVEL);
	}
}
