package com.pivo.hotelo.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pivo.hotelo.domain.exception.EntityInUseException;
import com.pivo.hotelo.domain.exception.HotelNotFoundException;
import com.pivo.hotelo.domain.model.Hotel;
import com.pivo.hotelo.domain.repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	public List<Hotel> findAll() {
		return hotelRepository.findAll();
	}
	
	public Hotel findOne(Long hotelId) {
		return hotelRepository.findById(hotelId).orElseThrow(
				()-> new HotelNotFoundException(hotelId));
	}

	@Transactional
	public Hotel add(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	@Transactional
	public void delete(Long hotelId) {
		try {
			findOne(hotelId);
			hotelRepository.deleteById(hotelId);
			hotelRepository.flush();
		}
		catch(DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Hotel de codigo %s não pode ser deletado pois esta em uso", hotelId));
		}			
	}
}

