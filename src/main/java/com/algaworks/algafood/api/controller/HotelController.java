package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Hotel;
import com.algaworks.algafood.domain.service.HotelService;

@RestController
@RequestMapping(value = "/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@GetMapping()
	public List<Hotel> findAll() {
		return hotelService.findAll(); 	
	}
	
	@GetMapping(value = "/{hotelId}")
	public Hotel findOne(@PathVariable Long hotelId) {
		return hotelService.findOne(hotelId); 	
	}
	
	@PostMapping()
	public Hotel Add(@RequestBody Hotel hotel) {
		
		return hotel;
	}
}
