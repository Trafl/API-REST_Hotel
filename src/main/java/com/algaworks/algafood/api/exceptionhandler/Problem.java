package com.algaworks.algafood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Problem {

	private Integer status;
	private String title;
	private String detail;
	private OffsetDateTime timestamp;
	private List<Field> fields;

	@Getter
	@Builder
	public static class Field {
	
		private String name;
		private String userMessage;
	
	}
}
