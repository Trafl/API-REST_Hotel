package com.algaworks.algafood.infrastructure.cloud;

public class StorageCloudException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StorageCloudException(String message, Throwable cause) {
		super(message, cause);
	}

	public StorageCloudException(String message) {
		super(message);
	}
}
