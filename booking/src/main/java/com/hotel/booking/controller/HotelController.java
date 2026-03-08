package com.hotel.booking.controller;


import com.hotel.booking.entity.Room;
import com.hotel.booking.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    @Autowired
    public HotelService hotelService;

    @PostMapping("/addRoom")
    public String addRoom(@Valid @RequestBody Room room) {
     return hotelService.addRoom(room);

    }
    @GetMapping("/getAllRooms")
    public List<Room> getAllRooms(){

        return hotelService.getAllRooms();
    }
    @GetMapping("/getRoom/{roomId}")
    public Room getRoomById(@PathVariable Integer roomId)
    {
        return hotelService.getRoomById(roomId);
    }
    @DeleteMapping("/deleteRoom/{roomId}")
    public String deleteRoomById(@PathVariable Integer roomId)
    {
        return hotelService.deleteRoomById(roomId);
    }

    @PutMapping("/updateRoom/{roomId}")
    public String updateRoom(@PathVariable Integer roomId,  @Valid @RequestBody Room room) {

        return hotelService.updateRoom(roomId, room);
    }

    @PutMapping("/bookRoom/{roomId}")
    public String bookRoom(@PathVariable Integer roomId) {
    return hotelService.bookRoom(roomId);
    }

    @PutMapping("/checkoutRoom/{roomId}")
    public String checkoutRoom(@PathVariable Integer roomId){
        return hotelService.checkoutRoom(roomId);
    }

    }



