package com.hotel.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    private String customerName;
    private String customerPhone;
    private String checkInDate;
    private String checkOutDate;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
