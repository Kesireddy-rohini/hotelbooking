package com.hotel.booking.exception;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(Integer roomId){
       super("Room not found with ID " +roomId);
    }

}
