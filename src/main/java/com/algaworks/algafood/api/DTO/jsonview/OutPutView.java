package com.algaworks.algafood.api.DTO.jsonview;

import com.fasterxml.jackson.annotation.JsonView;

public interface OutPutView {
	
	@JsonView
	public interface HotelView {
		
	}
	
	@JsonView
	public interface RoomView {
		
	}
}
