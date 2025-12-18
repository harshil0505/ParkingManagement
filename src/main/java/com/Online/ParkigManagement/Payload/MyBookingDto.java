package com.Online.ParkigManagement.Payload;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.Online.ParkigManagement.model.Address;
import com.Online.ParkigManagement.model.BookingStatus;
import com.Online.ParkigManagement.model.MyBooking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyBookingDto {
  private Long MyBookingId;
    private String parkingname; 
    private String address;
   
    private String vehicleNumber;
    private String vehicleType;
    private Integer duration;
    private double fee;
    private LocalDate  FeePaidDate;
    private BookingStatus status;

    private LocalDateTime actionTime = LocalDateTime.now();

   
}
