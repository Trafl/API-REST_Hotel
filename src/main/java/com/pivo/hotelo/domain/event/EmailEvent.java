package com.pivo.hotelo.domain.event;

import com.pivo.hotelo.domain.model.Rent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailEvent {

	private Rent rent;
}
