package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algafood.domain.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
