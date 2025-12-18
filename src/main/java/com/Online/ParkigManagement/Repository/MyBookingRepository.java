package com.Online.ParkigManagement.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Online.ParkigManagement.model.BookingStatus;
import com.Online.ParkigManagement.model.MyBooking;
public interface MyBookingRepository
        extends JpaRepository<MyBooking, Long> {

   
    List<MyBooking> findByVehicalNumber(String vehicalNumber);

  
    List<MyBooking> findByVehicalNumberAndStatus(
            String vehicalNumber,
            BookingStatus status
    );

   
    List<MyBooking> findByVehicalNumberOrderByActionTimeDesc(
            String vehicalNumber
    );


    boolean existsByVehicalNumberAndStatus(String vehicalNumber, BookingStatus active);
}
