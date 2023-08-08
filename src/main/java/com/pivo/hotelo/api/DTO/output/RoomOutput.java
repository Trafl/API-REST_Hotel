package com.pivo.hotelo.api.DTO.output;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.pivo.hotelo.api.DTO.jsonview.OutPutView;
import com.pivo.hotelo.domain.model.StatusType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomOutput {

	@Schema(example = "2")
	@JsonView(OutPutView.RoomView.class)
	private Integer code;
	
	@Schema(example = "300")
	@JsonView(OutPutView.RoomView.class)
	private BigDecimal diaria;

	@Schema(example = "Suite presidencial")
	@JsonView(OutPutView.RoomView.class)
	private String descricao;
	
	@Schema(example = "DISPONIVEL")
	@JsonView(OutPutView.RoomView.class)
	private StatusType status;
	
	private HotelOutputId hotel;
}
