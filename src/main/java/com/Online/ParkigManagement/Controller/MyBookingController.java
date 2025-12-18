package com.Online.ParkigManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Online.ParkigManagement.Payload.MyBookingDto;
import com.Online.ParkigManagement.Service.MyBookingService;

@RestController
@RequestMapping("/api/mybooking")
public class MyBookingController {

   @Autowired
   private MyBookingService myBookingService;
    @GetMapping("/all/{vehicalNumber}")
    public ResponseEntity<List<MyBookingDto>> allBookings(
            @PathVariable String vehicalNumber) {
        return ResponseEntity.ok(
                myBookingService.AllBookings(vehicalNumber)
        );
    }

    @GetMapping("/active/{vehicalNumber}")
    public ResponseEntity<List<MyBookingDto>> activeBooking(
            @PathVariable String vehicalNumber) {
        return ResponseEntity.ok(
                myBookingService.ActiveBooking(vehicalNumber)
        );
    }

    @GetMapping("/completed/{vehicalNumber}")
    public ResponseEntity<List<MyBookingDto>> completedBooking(
            @PathVariable String vehicalNumber) {
        return ResponseEntity.ok(
                myBookingService.ComoletedBooking(vehicalNumber)
        );
    }

    @GetMapping("/cancelled/{vehicalNumber}")
    public ResponseEntity<List<MyBookingDto>> cancelledBooking(
            @PathVariable String vehicalNumber) {
        return ResponseEntity.ok(
                myBookingService.CancelledBooking(vehicalNumber)
        );
    }
}
