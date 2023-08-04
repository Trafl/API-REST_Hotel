package com.pivo.hotelo.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pivo.hotelo.domain.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

	@Query("from Room where hotel.id = :hotel and id = :room")
    Optional<Room> findOneRoomFromHotelById(@Param("hotel") Long hotelId, 
            @Param("room") Long roomId);
}
