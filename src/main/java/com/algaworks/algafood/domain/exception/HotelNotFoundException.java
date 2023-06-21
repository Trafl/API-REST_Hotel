package com.algaworks.algafood.domain.exception;

public class HotelNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public HotelNotFoundException(String message) {
		super(message);
	}
}
