package com.pivo.hotelo.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	ROOM_NOT_FOUND("Quarto não esta registrado."),
	HOTEL_NOT_FOUND("Hotel não esta registrado."),
	CLIENT_NOT_FOUND("Cliente não esta registrado."),
	RENT_NOT_FOUND("Reserva não esta registrada."),
	ROOM_HOTEL_NOT_FOUND("Quarto não registrado nesse hotel"),
	ENTITY_IN_USE("Entidade em uso."),
	BUSINESS("Violação de regra de negócio"),
	UNREADABLE_MESSAGE("Mensagem incompreensível."),
	INVALID_DATA("Dados invalidos."),
	AMAZON_S3("Erro na comunicação com a AWS");

	private String title;
	
	ProblemType(String title) {
		this.title = title;
	}
}
