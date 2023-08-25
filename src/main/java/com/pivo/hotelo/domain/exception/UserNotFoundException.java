package com.pivo.hotelo.domain.exception;

public class UserNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}
	
	public UserNotFoundException(Long userId) {
		super(String.format("Usuario de codigo %s não foi encontrado nos registros.", userId));
	}
}
