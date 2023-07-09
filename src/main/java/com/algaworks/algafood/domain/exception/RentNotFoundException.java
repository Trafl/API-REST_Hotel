package com.algaworks.algafood.domain.exception;

public class RentNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public RentNotFoundException(String message) {
		super(message);
	}
	
	public RentNotFoundException(Long rentId) {
		super(String.format("Reserva de codigo %s não esta registada no sistema ", rentId));
	}
}
