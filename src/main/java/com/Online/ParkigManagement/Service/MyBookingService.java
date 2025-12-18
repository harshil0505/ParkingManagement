package com.Online.ParkigManagement.Service;

import java.util.List;

import com.Online.ParkigManagement.Payload.MyBookingDto;

public interface MyBookingService {

    List<MyBookingDto> AllBookings(String vehicalNumber);

    List<MyBookingDto> ActiveBooking(String vehicalNumber);

    List<MyBookingDto> ComoletedBooking(String vehicalNumber);

    List<MyBookingDto> CancelledBooking(String vehicalNumber);

   

}
