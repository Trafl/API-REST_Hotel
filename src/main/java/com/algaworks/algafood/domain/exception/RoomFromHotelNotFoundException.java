package com.algaworks.algafood.domain.exception;

public class RoomFromHotelNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public RoomFromHotelNotFoundException(String message) {
		super(message);
	}
	public RoomFromHotelNotFoundException(Long roomId, Long hotelId) {
		super(String.format("Quarto de codigo %s não esta registando no hotel de codigo %s.", roomId, hotelId));
	}
}
