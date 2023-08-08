package com.pivo.hotelo.api.DTO.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientOutput {
	
	@Schema(example = "Alex") 
	private String nome;
	
	@Schema(example = "alex@email.com") 
	private String email;
	
	@Schema(example = "(00)000000000") 
	private String celular;
	
	@Schema(example = "000.000.000-00") 
	private String cpf;
	
	@Schema(example = "00.000.000-0") 
	private String rg;	
}
