package com.algaworks.algafood.domain.exception;

public class RoomNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public RoomNotFoundException(String message) {
		super(message);
	}
	public RoomNotFoundException(Long roomId) {
		super(String.format("Quarto de codigo %s não foi encontado nos registros.", roomId));
	}
}
