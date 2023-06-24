package com.algaworks.algafood.api.exceptionhandler;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Problem {

	private OffsetDateTime timestamp;
	private String message;
	
}
