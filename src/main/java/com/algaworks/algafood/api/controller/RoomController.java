package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.DTO.input.RoomInput;
import com.algaworks.algafood.api.DTO.output.RoomOutput;
import com.algaworks.algafood.api.assembler.RoomMapper;
import com.algaworks.algafood.domain.model.Room;
import com.algaworks.algafood.domain.service.RoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/quarto")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomMapper roomMapper;
	
	@GetMapping()
	public List<RoomOutput> findAll() {
		return roomMapper.toCollectionOuputModel(roomService.findAll()); 	
	}
	
	@GetMapping(value = "/{roomId}")
	public RoomOutput findOne(@PathVariable Long roomId) {
		return roomMapper.toOutputModel(roomService.findOne(roomId)); 	
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public RoomOutput add(@RequestBody @Valid RoomInput roomInput) {
		Room room = roomMapper.toDomainModel(roomInput);
		room = roomService.add(room);
		return roomMapper.toOutputModel(room);
	}
	
	@DeleteMapping(value = "/{roomId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long roomId) {
		roomService.delete(roomId);
	}
}
