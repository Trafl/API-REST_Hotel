package com.pivo.hotelo.api.DTO.output;

import com.fasterxml.jackson.annotation.JsonView;
import com.pivo.hotelo.api.DTO.jsonview.OutPutView;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressOutput {

	@Schema(example = "22.574-040")
	@JsonView(OutPutView.HotelView.class)
	private String cep;
	
	@Schema(example = "245")
	@JsonView(OutPutView.HotelView.class)
	private String numero;

	@Schema(example = "casa")
	@JsonView(OutPutView.HotelView.class)
	private String complemento;

	@Schema(example = "japuiba")
	@JsonView(OutPutView.HotelView.class)
	private String bairro;
	
	@Schema(example = "Angra dos reis")
	@JsonView(OutPutView.HotelView.class)
	private String cidade;
	
}
