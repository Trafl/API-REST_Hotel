package com.pivo.hotelo.domain.exception;

public class GroupNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public GroupNotFoundException(String message) {
		super(message);
	}
	
	public GroupNotFoundException(Long groupId) {
		super(String.format("Grupo de codigo %s não foi encontrado nos registros.", groupId));
	}
}
