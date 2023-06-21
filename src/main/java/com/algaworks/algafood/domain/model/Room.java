package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private Integer code;
	
	private BigDecimal diaria;

	private String descricao;
	
	@CreationTimestamp
	private OffsetDateTime checkIn;
	
	private OffsetDateTime checkOut;
	
	private Boolean disponivel= true;
	
	@ManyToOne()
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;
}
