package com.pivo.hotelo.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

	@GetMapping
	@Operation(hidden = true)
	public RootEntryPointModel root() {
		
		var rootEntryPointModel = new RootEntryPointModel();
		
		rootEntryPointModel.add(linkTo(methodOn(HotelController.class).findAll()).withRel("hoteis"));
		rootEntryPointModel.add(linkTo(methodOn(ClientController.class).findAll()).withRel("clientes"));
		rootEntryPointModel.add(linkTo(methodOn(RentController.class).findAll()).withRel("reservas"));
		
		
		return rootEntryPointModel;
	}
	
	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel>{
		
	}
}
