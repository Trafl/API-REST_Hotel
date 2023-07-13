package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.DTO.input.HotelInput;
import com.algaworks.algafood.api.DTO.jsonview.OutPutView;
import com.algaworks.algafood.api.DTO.output.HotelOutput;
import com.algaworks.algafood.api.assembler.HotelMapper;
import com.algaworks.algafood.domain.model.Hotel;
import com.algaworks.algafood.domain.service.HotelService;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/hoteis")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private HotelMapper hotelMapper;
	
	@GetMapping()
	@JsonView(OutPutView.HotelView.class)
	public List<HotelOutput> findAll() {
		return hotelMapper.toCollectiononeOutputModel(hotelService.findAll()); 	
	}
	
	@GetMapping(value = "/{hotelId}")
	public HotelOutput findOne(@PathVariable Long hotelId) {
		return hotelMapper.toOutputModel(hotelService.findOne(hotelId)); 	
	}
	
	@PostMapping()
	@JsonView(OutPutView.HotelView.class)
	@ResponseStatus(HttpStatus.CREATED)
	public HotelOutput add(@RequestBody @Valid HotelInput hotelInput) {
		Hotel hotel = hotelMapper.toDomainModel(hotelInput);
		return  hotelMapper.toOutputModel(hotelService.add(hotel));
	}
	
	@PutMapping(value = "/{hotelId}")
	@JsonView(OutPutView.HotelView.class)
	public HotelOutput update(@RequestBody @Valid HotelInput hotelInput, @PathVariable Long hotelId) {	
		Hotel hotel = hotelService.findOne(hotelId);
		hotelMapper.copyToDomain(hotelInput, hotel);
		hotelService.add(hotel);
		
		return hotelMapper.toOutputModel(hotel);
	}
	
	
	@DeleteMapping(value = "/{hotelId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long hotelId) {
		hotelService.delete(hotelId);
	}
}
