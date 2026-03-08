package com.hotel.booking.service;

import com.hotel.booking.dto.RoomRequestDTO;
import com.hotel.booking.dto.RoomResponseDTO;
import com.hotel.booking.entity.Room;
import com.hotel.booking.repo.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelServiceDTO {

    @Autowired
    HotelRepo hotelRepo;

    //addroom
    public RoomResponseDTO addRoom(RoomRequestDTO roomRequestDTO) {

        //convert RequestDto to Entity
        Room room = new Room();
        room.setRoomNumber(roomRequestDTO.getRoomNumber());
        room.setRoomType(roomRequestDTO.getRoomType());
        room.setPricePerNight(roomRequestDTO.getPricePerNight());
        room.setAvailable(roomRequestDTO.isAvailable());
        room.setMaxPeople(roomRequestDTO.getMaxPeople());
        room.setMaintenanceCost(roomRequestDTO.getMaintenanceCost());
        room.setCleaningStatus(roomRequestDTO.getCleaningStatus());

        //save to database
        Room savedRoom = hotelRepo.save(room);

        // convert Entity to ResonseDto
        RoomResponseDTO roomResponseDTO = new RoomResponseDTO();
        roomResponseDTO.setRoomId(savedRoom.getRoomId());
        roomResponseDTO.setRoomNumber(savedRoom.getRoomNumber());
        roomResponseDTO.setRoomType(savedRoom.getRoomType());
        roomResponseDTO.setPricePerNight(savedRoom.getPricePerNight());
        roomResponseDTO.setAvailable(savedRoom.isAvailable());
        roomResponseDTO.setMaxPeople(savedRoom.getMaxPeople());
        roomResponseDTO.setMaintenanceCost(savedRoom.getMaintenanceCost());
        roomResponseDTO.setCleaningStatus(savedRoom.getCleaningStatus());
        return roomResponseDTO;
    }

    //get all rooms
    public List<RoomResponseDTO> getAllRooms() {

        // step -1 Get all rooms from DB as entity list
        List<Room> rooms = hotelRepo.findAll();

        // step -2 Create empty ResponseDTO list
        List<RoomResponseDTO> responseDTOS = new ArrayList<>();

        // step -3  Loop through each room and convert to ResponseDTO
        for (Room room : rooms) {
            RoomResponseDTO roomResponseDTO = new RoomResponseDTO();

            roomResponseDTO.setRoomId(room.getRoomId());
            roomResponseDTO.setRoomNumber(room.getRoomNumber());
            roomResponseDTO.setRoomType(room.getRoomType());
            roomResponseDTO.setPricePerNight(room.getPricePerNight());
            roomResponseDTO.setAvailable(room.isAvailable());
            roomResponseDTO.setMaxPeople(room.getMaxPeople());
            roomResponseDTO.setMaintenanceCost(room.getMaintenanceCost());
            roomResponseDTO.setCleaningStatus(room.getCleaningStatus());

            responseDTOS.add(roomResponseDTO);
        }
        return responseDTOS;
    }


    //get room by ID
    public RoomResponseDTO getRoomById(Integer roomId) {

        //step -1 find room from database
        Room room = hotelRepo.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));

        //step -2 covert entity to responsedto
        RoomResponseDTO roomResponseDTO = new RoomResponseDTO();

        roomResponseDTO.setRoomId(room.getRoomId());
        roomResponseDTO.setRoomNumber(room.getRoomNumber());
        roomResponseDTO.setRoomType(room.getRoomType());
        roomResponseDTO.setPricePerNight(room.getPricePerNight());
        roomResponseDTO.setAvailable(room.isAvailable());
        roomResponseDTO.setMaxPeople(room.getMaxPeople());
        roomResponseDTO.setMaintenanceCost(room.getMaintenanceCost());
        roomResponseDTO.setCleaningStatus(room.getCleaningStatus());

        // step -3 return to roomresponsedto
        return roomResponseDTO;

    }

    //update room

    public RoomResponseDTO updateRoom(Integer roomId, RoomRequestDTO roomRequestDTO) {

        // Step 1 - Find existing room from DB
        Room existing = hotelRepo.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));

        // Step 2 - Update entity fields with roomRequestDTO values
        existing.setRoomNumber(roomRequestDTO.getRoomNumber());
        existing.setRoomType(roomRequestDTO.getRoomType());
        existing.setPricePerNight(roomRequestDTO.getPricePerNight());
        existing.setAvailable(roomRequestDTO.isAvailable());
        existing.setMaxPeople(roomRequestDTO.getMaxPeople());
        existing.setMaintenanceCost(roomRequestDTO.getMaintenanceCost());
        existing.setCleaningStatus(roomRequestDTO.getCleaningStatus());

        // Step 3 - Save updated entity to Database
        Room updatedRoom = hotelRepo.save(existing);

        // Step 4 - Convert updated entity to ResponseDTO
        RoomResponseDTO roomResponseDTO = new RoomResponseDTO();

        roomResponseDTO.setRoomId(updatedRoom.getRoomId());
        roomResponseDTO.setRoomNumber(updatedRoom.getRoomNumber());
        roomResponseDTO.setRoomType(updatedRoom.getRoomType());
        roomResponseDTO.setPricePerNight(updatedRoom.getPricePerNight());
        roomResponseDTO.setAvailable(updatedRoom.isAvailable());
        roomResponseDTO.setMaxPeople(updatedRoom.getMaxPeople());
        roomResponseDTO.setMaintenanceCost(updatedRoom.getMaintenanceCost());
        roomResponseDTO.setCleaningStatus(updatedRoom.getCleaningStatus());

        return roomResponseDTO;
    }

    //delete room

    public RoomResponseDTO deleteRoom(Integer roomId){
            Room room = hotelRepo.findById(roomId)
                    .orElseThrow(()-> new RuntimeException());

            RoomResponseDTO roomResponseDTO= new RoomResponseDTO();

            roomResponseDTO.setRoomId(room.getRoomId());
            roomResponseDTO.setRoomNumber(room.getRoomNumber());
            roomResponseDTO.setRoomType(room.getRoomType());
            roomResponseDTO.setPricePerNight(room.getPricePerNight());
            roomResponseDTO.setAvailable(room.isAvailable());
            roomResponseDTO.setMaxPeople(room.getMaxPeople());
            roomResponseDTO.setMaintenanceCost(room.getMaintenanceCost());
            roomResponseDTO.setCleaningStatus(room.getCleaningStatus());

            hotelRepo.delete(room);
            return roomResponseDTO;

    }
    //book room

    public RoomResponseDTO bookRoom(Integer roomId) {

        //step -1 find room from database
        Room room = hotelRepo.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with ID" + roomId));

        // step 2 - check room is available

        if (!room.isAvailable())
            return null;

        // mark as booked
        room.setAvailable(false);
        Room bookedRoom = hotelRepo.save(room);

        // Step 4 - Convert entity to ResponseDTO
        RoomResponseDTO roomResponseDTO = new RoomResponseDTO();
        roomResponseDTO.setRoomId(bookedRoom.getRoomId());
        roomResponseDTO.setRoomNumber(bookedRoom.getRoomNumber());
        roomResponseDTO.setRoomType(bookedRoom.getRoomType());
        roomResponseDTO.setPricePerNight(bookedRoom.getPricePerNight());
        roomResponseDTO.setAvailable(bookedRoom.isAvailable());
        roomResponseDTO.setMaxPeople(bookedRoom.getMaxPeople());
        roomResponseDTO.setMaintenanceCost(bookedRoom.getMaintenanceCost());
        roomResponseDTO.setCleaningStatus(bookedRoom.getCleaningStatus());
        return roomResponseDTO;
    }

    //checkout room

    public RoomResponseDTO checkoutRoom(Integer roomId) {

        // Step 1 - Find room from Database
        Room room = hotelRepo.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with Id" + roomId));

        // step -2 mark as vacant
        room.setAvailable(true);
        Room checkedOutRoom = hotelRepo.save(room);

        //step -3 convert entity to responsedto

        RoomResponseDTO roomResponseDTO = new RoomResponseDTO();
        roomResponseDTO.setRoomId(checkedOutRoom.getRoomId());
        roomResponseDTO.setRoomNumber(checkedOutRoom.getRoomNumber());
        roomResponseDTO.setRoomType(checkedOutRoom.getRoomType());
        roomResponseDTO.setPricePerNight(checkedOutRoom.getPricePerNight());
        roomResponseDTO.setAvailable(checkedOutRoom.isAvailable());
        roomResponseDTO.setMaxPeople(checkedOutRoom.getMaxPeople());
        roomResponseDTO.setMaintenanceCost(checkedOutRoom.getMaintenanceCost());
        roomResponseDTO.setCleaningStatus(checkedOutRoom.getCleaningStatus());

        return roomResponseDTO;

    }




}
