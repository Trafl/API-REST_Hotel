package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.DTO.HotelOutput;
import com.algaworks.algafood.api.assembler.HotelIMapper;
import com.algaworks.algafood.domain.model.Hotel;
import com.algaworks.algafood.domain.service.HotelService;

@RestController
@RequestMapping(value = "/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private HotelIMapper hotelMapper;
	
	@GetMapping()
	public List<HotelOutput> findAll() {
		return hotelMapper.ToCollectionInputModel(hotelService.findAll()); 	
	}
	
	@GetMapping(value = "/h")
	public List<Hotel>find() {
		return hotelService.findAll(); 	
	}
	
	@GetMapping(value = "/{hotelId}")
	public HotelOutput findOne(@PathVariable Long hotelId) {
		return hotelMapper.ToOutputModel(hotelService.findOne(hotelId)); 	
	}
	
	@PostMapping()
	public Hotel Add(@RequestBody Hotel hotel) {
		
		return hotel;
	}
}
