package com.algaworks.algafood.api.DTO.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelInputId {

	@NotNull
	private Long id;
}
