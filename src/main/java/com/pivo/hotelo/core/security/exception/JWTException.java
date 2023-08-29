package com.pivo.hotelo.core.security.exception;

import com.pivo.hotelo.domain.exception.BusinessException;

public class JWTException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public JWTException(String message) {
		super(message);
	}
}
