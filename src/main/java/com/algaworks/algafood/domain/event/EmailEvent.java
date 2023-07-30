package com.algaworks.algafood.domain.event;

import com.algaworks.algafood.domain.model.Rent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailEvent {

	private Rent rent;
}
