package com.hotel.booking.repo;


import com.hotel.booking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<Room, Integer> {
}
