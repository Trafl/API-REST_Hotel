package com.pivo.hotelo.api.DTO.output;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.pivo.hotelo.api.DTO.jsonview.OutPutView;
import com.pivo.hotelo.domain.model.StatusType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomOutput {

	@JsonView(OutPutView.RoomView.class)
	private Integer code;
	
	@JsonView(OutPutView.RoomView.class)
	private BigDecimal diaria;

	@JsonView(OutPutView.RoomView.class)
	private String descricao;
	
	@JsonView(OutPutView.RoomView.class)
	private StatusType status;
	
	private HotelOutputId hotel;
}
