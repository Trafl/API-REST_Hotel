package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;

public class RoomHotelService {

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private HotelService hotelService;
	
}
