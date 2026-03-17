package com.hotel.booking.repo;

import com.hotel.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
}
