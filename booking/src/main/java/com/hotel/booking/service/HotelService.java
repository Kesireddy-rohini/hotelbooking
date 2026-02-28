package com.hotel.booking.service;


import com.hotel.booking.entity.Room;
import com.hotel.booking.exception.RoomNotFoundException;
import com.hotel.booking.repo.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    HotelRepo hotelRepo;

    // add new room
    public String addRoom(Room room){
        hotelRepo.save(room);
    return "room added successfully";
    }
 // get all rooms
    public List<Room> getAllRooms(){

        return hotelRepo.findAll();
    }

//get room by id
    public Room getRoomById(Integer roomId){
        return hotelRepo.findById(roomId)
         .orElseThrow(() -> new RoomNotFoundException(roomId));
    }

    //delete room
    public String deleteRoomById(Integer roomId){
          hotelRepo.deleteById(roomId);
                return "room deleted successfully";
    }

    //update room
    public String updateRoom(Integer roomId, Room room){
        Room existing = hotelRepo.findById(roomId).get();

        existing.setRoomNumber(room.getRoomNumber());
        existing.setRoomType(room.getRoomType());
        existing.setPricePerNight(room.getPricePerNight());
        existing.setAvailable(room.isAvailable());
        existing.setMaxPeople(room.getMaxPeople());
        hotelRepo.save(existing);
        return "room updated successfully";
    }

    //book room

    public String bookRoom(Integer roomId){

        Room room = hotelRepo.findById(roomId).get();
        if(!room.isAvailable()){
            return "Room is already booked";
        }
        room.setAvailable(false);
        hotelRepo.save(room);
        return "Room booked successfully";
    }

    //Checkout room
    public String checkoutRoom(Integer roomId){
        Room room = hotelRepo.findById(roomId).get();
        room.setAvailable(true);
        hotelRepo.save(room);
        return "Checkout Successfully, Room is now available";
    }
}
