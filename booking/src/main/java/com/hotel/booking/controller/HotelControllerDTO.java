package com.hotel.booking.controller;


import com.hotel.booking.dto.RoomRequestDTO;
import com.hotel.booking.dto.RoomResponseDTO;
import com.hotel.booking.service.HotelServiceDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dto")
public class HotelControllerDTO {

    @Autowired
    HotelServiceDTO hotelServiceDTO;

    @PostMapping("/addroom")
    public RoomResponseDTO addRoom(@RequestBody RoomRequestDTO roomRequestDTO){

        return hotelServiceDTO.addRoom(roomRequestDTO);

    }

    @GetMapping("/getAllRooms")
    public List<RoomResponseDTO> getAllRooms(){
       return hotelServiceDTO.getAllRooms();
    }

    @GetMapping("/getRoom/{roomId}")
    public RoomResponseDTO getRoomById(@PathVariable Integer roomId){
        return hotelServiceDTO.getRoomById(roomId);
    }

    @PutMapping("/updateRoom/{roomId}")
    public RoomResponseDTO updateRoom(@PathVariable Integer roomId,@Valid @RequestBody RoomRequestDTO roomRequestDTO){

        return hotelServiceDTO.updateRoom(roomId, roomRequestDTO);
    }

    @DeleteMapping("/deleteRoom/{roomId}")
    public RoomResponseDTO deleteRoom(@PathVariable Integer roomId){
        return hotelServiceDTO.deleteRoom(roomId);
    }


    @PutMapping("/bookRoom/{roomId}")
    public RoomResponseDTO bookRoom(@PathVariable Integer roomId){
        return hotelServiceDTO.bookRoom(roomId);
    }

    @PutMapping("/checkOut/{roomId}")
    public RoomResponseDTO checkOutRoom(@PathVariable Integer roomId){
        return hotelServiceDTO.checkoutRoom(roomId);
    }
}
