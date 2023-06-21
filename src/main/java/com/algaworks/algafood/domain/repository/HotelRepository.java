package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.algaworks.algafood.domain.model.Hotel;
import com.algaworks.algafood.domain.model.Room;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

	@Query("select r from Room r where r.hotel.id = ?1")
	public Room findHotelRoom(Long id);
}
