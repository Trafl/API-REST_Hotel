package com.pivo.hotelo.domain.exception;

public class PermissionNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public PermissionNotFoundException(String message) {
		super(message);
	}
	
	public PermissionNotFoundException(Long permissionId) {
		super(String.format("Permissão de codigo %s não foi encontrado nos registros.", permissionId));
	}
}
