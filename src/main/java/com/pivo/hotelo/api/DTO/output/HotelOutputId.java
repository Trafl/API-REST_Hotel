package com.pivo.hotelo.api.DTO.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelOutputId {

	@Schema(example = "1")
	private Long id;
}
