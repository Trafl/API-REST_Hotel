package com.pivo.hotelo.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pivo.hotelo.domain.model.Rent;

public interface RentRepository extends JpaRepository<Rent, Long> {

	@Query("from Rent where cliente.id = :cliente ")
	public Optional<List<Rent>> findRentByClient(@Param("cliente") Long clientId);
	
	
}
