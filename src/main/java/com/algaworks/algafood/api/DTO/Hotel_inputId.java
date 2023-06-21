package com.algaworks.algafood.api.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hotel_inputId {

	@NotNull
	private Long id;
}
