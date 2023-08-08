package com.pivo.hotelo.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Schema(name = "Problema")
public class Problem {

	@Schema(example = "400")
	private Integer status;
	
	@Schema(example = "Dados invalidos")
	private String title;
	
	@Schema(example = "Erro ao validar os campos informados no corpo da resposta.")
	private String detail;
	
	@Schema(example = "2023-08-01T10:00:00.7604936-03:00")
	private OffsetDateTime timestamp;
	
	@Schema(description = "Lista de campos que geraram o erro")
	private List<Field> fields;

	@Getter
	@Builder
	@Schema(name = "ListaProblema")
	public static class Field {
	
		@Schema(example = "nome")
		private String name;
		
		@Schema(example = "Nome da entidade é obrigatória.")
		private String userMessage;
	
	}
}
