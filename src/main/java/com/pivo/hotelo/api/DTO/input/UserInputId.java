package com.pivo.hotelo.api.DTO.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInputId {

	@Schema(example = "1")
	@NotNull
	private Long id;
	
}
