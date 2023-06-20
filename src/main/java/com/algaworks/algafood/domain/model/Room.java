package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	
	@Column(name = "diaria")
	private BigDecimal daily;
	
	@Column(name = "descricao")
	private String description;
	
	@CreationTimestamp
	private OffsetDateTime checkIn;
	
	private OffsetDateTime checkOut;
	
	@Column(name = "disponivel")
	private Boolean available= true;
	
	@ManyToOne()
	private Hotel hotel;
}
