package com.pivo.hotelo.domain.exception;

public class EmailInUseException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public EmailInUseException(String message) {
		super(message);
	}
}
