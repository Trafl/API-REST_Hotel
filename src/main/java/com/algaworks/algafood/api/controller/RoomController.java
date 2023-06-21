package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.DTO.RoomInput;
import com.algaworks.algafood.api.DTO.RoomOutput;
import com.algaworks.algafood.api.assembler.RoomIMapper;
import com.algaworks.algafood.domain.model.Room;
import com.algaworks.algafood.domain.service.RoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/quarto")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomIMapper roomMapper;
	
	@GetMapping()
	public List<RoomOutput> findAll() {
		return roomMapper.ToCollectionOuputModel(roomService.findAll()); 	
	}
	
	@GetMapping(value = "/{roomId}")
	public RoomOutput findOne(@PathVariable Long roomId) {
		return roomMapper.ToOutputModel(roomService.findOne(roomId)); 	
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public RoomOutput Add(@RequestBody @Valid RoomInput roomInput) {
		Room room = roomMapper.ToDomainModel(roomInput);
		room = roomService.add(room);
		return roomMapper.ToOutputModel(room);
	}
}
