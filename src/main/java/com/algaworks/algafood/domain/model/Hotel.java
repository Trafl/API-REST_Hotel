package com.algaworks.algafood.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "nome")
	private String name;

	@Column(name = "descricao")
	private String description;
	
	@Embedded
	private Address address; //Podendo integrar com uma API de Localização 
	
	@OneToMany(mappedBy = "hotel")
	@Column(name = "quartos")
	private Set<Room> room = new HashSet<>(); 
	
/* Atributos pendentes, Avaliação dos clientes, Classificação */
	
}
