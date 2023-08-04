package com.pivo.hotelo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pivo.hotelo.domain.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
