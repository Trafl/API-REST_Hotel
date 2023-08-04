package com.pivo.hotelo.api.DTO.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientInputId {

	@NotNull
	private Long id;
}
