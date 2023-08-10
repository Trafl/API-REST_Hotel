package com.pivo.hotelo.api.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pivo.hotelo.api.DTO.input.HotelInput;
import com.pivo.hotelo.api.DTO.output.HotelOutput;
import com.pivo.hotelo.api.DTO.output.HotelSummaryOutput;
import com.pivo.hotelo.api.assembler.HotelMapper;
import com.pivo.hotelo.api.assembler.HotelSummaryMapper;
import com.pivo.hotelo.api.controller.openapimodel.HotelControllerOpenApi;
import com.pivo.hotelo.domain.model.Hotel;
import com.pivo.hotelo.domain.service.HotelService;

import jakarta.validation.Valid;

@CrossOrigin(methods = RequestMethod.GET)
@RestController
@RequestMapping(value = "/hotel")
public class HotelController implements HotelControllerOpenApi {

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private HotelMapper hotelMapper;
	
	@Autowired
	private HotelSummaryMapper hotelSummaryMapper;
	
	@GetMapping()
	public ResponseEntity<CollectionModel<HotelSummaryOutput>> findAll() {
		
		CollectionModel<HotelSummaryOutput> list =  hotelSummaryMapper.toCollectionModel(hotelService.findAll()); 
		
		CollectionModel<HotelSummaryOutput> hoteis = CollectionModel.of(list);
		
		return	ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(20, TimeUnit.SECONDS))
				.body(hoteis);
	}
	
	@GetMapping(value = "/{hotelId}")
	public ResponseEntity<HotelOutput> findOne(@PathVariable Long hotelId) {
		HotelOutput hotel = hotelMapper.toModel(hotelService.findOne(hotelId)); 
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(20, TimeUnit.SECONDS))
				.body(hotel);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public HotelSummaryOutput add(@RequestBody @Valid HotelInput hotelInput) {
		Hotel hotel = hotelMapper.toDomainModel(hotelInput);
		
		return  hotelSummaryMapper.toModel(hotelService.add(hotel));
	}
	
	@PutMapping(value = "/{hotelId}")

	public HotelSummaryOutput update(@RequestBody @Valid HotelInput hotelInput, @PathVariable Long hotelId) {	
		Hotel hotel = hotelService.findOne(hotelId);
		hotelSummaryMapper.copyToDomain(hotelInput, hotel);
		hotelService.add(hotel);
		
		return hotelSummaryMapper.toModel(hotel);
	}
	
	
	@DeleteMapping(value = "/{hotelId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long hotelId) {
		hotelService.delete(hotelId);
	}
}
