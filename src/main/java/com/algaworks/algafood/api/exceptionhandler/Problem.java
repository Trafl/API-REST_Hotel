package com.algaworks.algafood.api.exceptionhandler;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(content = Include.NON_NULL)
public class Problem {

	private Integer status;
	private String title;
	private String detail;
	
	private OffsetDateTime timestamp;

	
}
