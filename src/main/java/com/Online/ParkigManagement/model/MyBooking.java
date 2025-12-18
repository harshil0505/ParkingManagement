package com.Online.ParkigManagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "mybooking")
public class MyBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MyBookingId;




    @NonNull
    private String vehicalNumber;
    
    private String vehicalType;

 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address")
    private Address address; 
 
    private Integer duration; 
    private Double fee;
    private LocalDate feePaidDate;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    
    private LocalDateTime actionTime = LocalDateTime.now();

    
    
   
}