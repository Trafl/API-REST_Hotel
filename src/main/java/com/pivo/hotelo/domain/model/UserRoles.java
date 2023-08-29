package com.pivo.hotelo.domain.model;

public enum UserRoles {

	ADMIN("admin"),
	CLIENT("client");
	
	private String role;
	
	UserRoles(String string) {
		this.role = string;
	}

	public String getRole() {
		return role;
	}
	
}
