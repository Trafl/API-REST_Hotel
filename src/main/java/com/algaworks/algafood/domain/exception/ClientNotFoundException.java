package com.algaworks.algafood.domain.exception;

public class ClientNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public ClientNotFoundException(String message) {
		super(message);
	}
	
	public ClientNotFoundException(Long clientId) {
		super(String.format("Cliente de codigo %s não foi encontrado nos registros.", clientId));
	}
}
