package com.hotel.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomId;

   @NotNull(message = "Room number cannot be Null")
    private Integer roomNumber;

    @NotBlank(message = "Room type cannot be empty")
    private String roomType;

    @Positive(message = "Price cannot be negative")
    private double pricePerNight;

    private boolean available;

    @Min(value = 1,message = "At least one person required")
    private Integer maxPeople;

    @JsonIgnore
    private double maintenanceCost;

    @JsonIgnore
    private String cleaningStatus;

    @OneToMany(mappedBy = "room")

    @JsonIgnore
    private List<Booking> bookings;

}