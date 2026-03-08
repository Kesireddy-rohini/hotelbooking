package com.hotel.booking.dto;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponseDTO {

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

    @Positive(message = "Maintenance cost must be positive")
    private double maintenanceCost;

    @NotBlank(message = "CleaningStatus cannot be empty")
    private String cleaningStatus;
}