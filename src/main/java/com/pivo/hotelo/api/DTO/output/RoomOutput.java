package com.pivo.hotelo.api.DTO.output;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.pivo.hotelo.domain.model.StatusType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "quartos")
@Setter
@Getter
public class RoomOutput extends RepresentationModel<RoomOutput> {

	@Schema(example = "2")
	private Integer code;
	
	@Schema(example = "300")
	private BigDecimal diaria;

	@Schema(example = "Suite presidencial")
	private String descricao;
	
	@Schema(example = "DISPONIVEL")
	private StatusType status;
	
}
