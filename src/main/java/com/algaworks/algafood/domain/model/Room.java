package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
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
	
	private Boolean disponivel= true;
	
	@ManyToOne()
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;
	
	public void toHire() {
		setDisponivel(false);
	}
	
	public void toAvailable() {
		setDisponivel(true);
	}
}
