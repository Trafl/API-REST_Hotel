package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.algaworks.algafood.domain.model.RentRoom;

public interface RentRoomRepository extends JpaRepository<RentRoom, Long> {

	@Query("from RentRoom where cliente.id = :cliente ")
	public Optional<List<RentRoom>> findRentByClient(@Param("cliente") Long clientId);
}
