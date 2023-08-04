package com.pivo.hotelo.domain.exception;

public class HotelNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public HotelNotFoundException(String message) {
		super(message);
	}
	
	public HotelNotFoundException(Long hotelId) {
		super(String.format("Hotel de codigo %s não foi encontrado nos registros.", hotelId));
	}
}
