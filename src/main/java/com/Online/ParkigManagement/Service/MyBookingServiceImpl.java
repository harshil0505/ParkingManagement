package com.Online.ParkigManagement.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Online.ParkigManagement.Payload.MyBookingDto;
import com.Online.ParkigManagement.Repository.MyBookingRepository;
import com.Online.ParkigManagement.model.BookingStatus;
import com.Online.ParkigManagement.model.MyBooking;

    
@Service
public class MyBookingServiceImpl implements MyBookingService {

    @Autowired
    private MyBookingRepository myBookingRepository;

    @Override
    public List<MyBookingDto> AllBookings(String vehicalNumber) {
        return myBookingRepository.findByVehicalNumber(vehicalNumber)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MyBookingDto> ActiveBooking(String vehicalNumber) {
        return getByStatus(vehicalNumber, BookingStatus.ACTIVE);
    }

    @Override
    public List<MyBookingDto> ComoletedBooking(String vehicalNumber) {
        return getByStatus(vehicalNumber, BookingStatus.COMPLETED);
    }

    @Override
    public List<MyBookingDto> CancelledBooking(String vehicalNumber) {
        return getByStatus(vehicalNumber, BookingStatus.CANCELLED);
    }

    // ================= helper =================
    private List<MyBookingDto> getByStatus(
            String vehicalNumber,
            BookingStatus status) {

        return myBookingRepository
                .findByVehicalNumberAndStatus(vehicalNumber, status)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ================= mapper =================
    private MyBookingDto mapToDto(MyBooking booking) {

        MyBookingDto dto = new MyBookingDto();

        
        dto.setMyBookingId(booking.getMyBookingId());
        dto.setVehicleType(booking.getVehicalType());
    
        dto.setVehicleNumber(booking.getVehicalNumber());
        dto.setDuration(booking.getDuration());
        dto.setFee(booking.getFee());
        dto.setFeePaidDate(booking.getFeePaidDate());
        dto.setStatus(booking.getStatus());
        dto.setActionTime(booking.getActionTime());

        if (booking.getAddress() != null) {
            dto.setParkingname(booking.getAddress().getParkingName());
            dto.setAddress(
                    booking.getAddress().getStreet() + ", " +
                    booking.getAddress().getCity() + ", " +
                    booking.getAddress().getState() + " - " +
                    booking.getAddress().getPincode()
            );
        }

        return dto;
    }
}
