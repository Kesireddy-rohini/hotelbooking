package com.hotel.booking.controller;


import com.hotel.booking.entity.Booking;
import com.hotel.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/createBooking/{roomId}")
    public Booking createBooking(@PathVariable Integer roomId, @RequestBody Booking booking){
        return bookingService.createBooking(roomId, booking);
    }

    @GetMapping("/getAllBookings")
    public List<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @GetMapping("/getBooking/{roomId}")
    public Booking getBookingById(@PathVariable Integer roomId){
        return bookingService.getBookingById(roomId);
    }
}
