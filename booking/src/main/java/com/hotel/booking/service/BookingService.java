package com.hotel.booking.service;

import com.hotel.booking.entity.Booking;
import com.hotel.booking.entity.Room;
import com.hotel.booking.repo.BookingRepo;
import com.hotel.booking.repo.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private HotelRepo hotelRepo;

    public Booking createBooking(Integer roomId, Booking booking){

        Room room = hotelRepo.findById(roomId)
                .orElseThrow(()-> new RuntimeException("Room Id not found " +roomId));

        if (!room.isAvailable()){
            throw new RuntimeException("Room is already booked");
        }

        //link the room to the booking
        booking.setRoom(room);

        room.setAvailable(false);
        hotelRepo.save(room);

        return bookingRepo.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    public Booking getBookingById(Integer bookingId){
        return bookingRepo.findById(bookingId)
                .orElseThrow(()-> new RuntimeException("Booking not found with Id" + bookingId));

    }



}
