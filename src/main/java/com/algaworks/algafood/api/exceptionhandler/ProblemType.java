package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	ROOM_NOT_FOUND("Quarto não esta registrado."),
	HOTEL_NOT_FOUND("Hotel não esta registrado."),
	ROOM_HOTEL_NOT_FOUND("Quarto não registrado nesse hotel"),
	ENTITY_IN_USE("Entidade em uso."),
	BUSINESS("Violação de regra de negócio"),
	UNREADABLE_MESSAGE("Mensagem incompreensível.");

	private String title;
	
	ProblemType(String title) {
		this.title = title;
	}
}
