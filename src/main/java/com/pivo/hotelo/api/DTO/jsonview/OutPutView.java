package com.pivo.hotelo.api.DTO.jsonview;

import com.fasterxml.jackson.annotation.JsonView;

public interface OutPutView {
	
	@JsonView
	public interface HotelView {
		
	}
	
	@JsonView
	public interface RoomView {
		
	}
}
