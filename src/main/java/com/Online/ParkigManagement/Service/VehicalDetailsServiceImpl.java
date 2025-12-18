package com.Online.ParkigManagement.Service;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Online.ParkigManagement.Config.feeConfig;
import com.Online.ParkigManagement.Payload.VehicalDetailsDto;
import com.Online.ParkigManagement.Payload.VehicalDetailsResponse;
import com.Online.ParkigManagement.Repository.AddressRepository;
import com.Online.ParkigManagement.Repository.DriverDetailsRepository;
import com.Online.ParkigManagement.Repository.MyBookingRepository;
import com.Online.ParkigManagement.Repository.VehicalDetailsRepository;
import com.Online.ParkigManagement.excepasion.ResourceNotFoundException;
import com.Online.ParkigManagement.model.Address;
import com.Online.ParkigManagement.model.BookingStatus;
import com.Online.ParkigManagement.model.DriverDetails;
import com.Online.ParkigManagement.model.MyBooking;
import com.Online.ParkigManagement.model.VehicalDetails;

@Service
public class VehicalDetailsServiceImpl implements VehicalDetailsService {

     @Autowired
    ModelMapper modelMapper;

    @Autowired
    DriverDetailsRepository driverDetailsRepository;

    @Autowired
    feeConfig feeConfig;

   
    @Autowired
    private MyBookingRepository myBookingRepository;

    @Autowired
    VehicalDetailsRepository  vehicalDetailsRepository;

  

    @Autowired
    AddressRepository addressRepository;
 
   
    @Override
    public VehicalDetailsDto addDetalis(VehicalDetailsDto vehicalDetailsDto){




      VehicalDetails vehicalDetails=modelMapper.map(vehicalDetailsDto,VehicalDetails.class);
      vehicalDetails.getVehicalId();
      vehicalDetails.setVehicalId(null); 
      vehicalDetails.setDuration(0);
      vehicalDetails.setVehicalNumber(vehicalDetailsDto.getVehicalNumber());
      
      vehicalDetails.setFee(0.0);
      
 
    

            if (vehicalDetailsRepository.findByVehicalNumber(vehicalDetails.getVehicalNumber()).isPresent()) {
                throw new ResourceNotFoundException("VehicalDetails", "vehicalNumber", vehicalDetails.getVehicalNumber());
            }
            
           
      
      VehicalDetails savedDetails = vehicalDetailsRepository.save(vehicalDetails);
            return modelMapper.map(savedDetails, VehicalDetailsDto.class);

    }


    @Override
    public  VehicalDetailsResponse getAlldetails(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
       Sort sortByAnyOrder=sortDir.equalsIgnoreCase("asc")
       ?Sort.by(sortBy).ascending()
       :Sort.by(sortBy).descending();

       Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAnyOrder);
       Page<VehicalDetails>  VehicalPage=vehicalDetailsRepository.findAll(pageDetails);
       List<VehicalDetails> vehicalDetails=VehicalPage.getContent();

       List<VehicalDetailsDto> vehicalDetailsDtos=vehicalDetails.stream()
                 .map(driver -> modelMapper.map(driver, VehicalDetailsDto.class))
                .toList();
       VehicalDetailsResponse vehicalDetailsResponse=new VehicalDetailsResponse();
       vehicalDetailsResponse.setContent(vehicalDetailsDtos);
       vehicalDetailsResponse.setPageNumber(VehicalPage.getNumber());
       vehicalDetailsResponse.setPageSize(VehicalPage.getSize());
       vehicalDetailsResponse.setTotalElements(VehicalPage.getTotalElements());
       vehicalDetailsResponse.setTotalPages(VehicalPage.getTotalPages());
       vehicalDetailsResponse.setLastPage(VehicalPage.isLast());

       return vehicalDetailsResponse;
    }


  

    @Override
    public VehicalDetailsResponse getdetailsById(Long vehicalId, Integer pageNumber, Integer pageSize, String sortBy,
            String sortDir) {
              
              VehicalDetails VehicalDetails = vehicalDetailsRepository.findById(vehicalId)
              .orElseThrow(() -> new ResourceNotFoundException("VehicalDetails", "vehicalId", vehicalId));
          
          VehicalDetailsDto VehicalDetailsDto = modelMapper.map(VehicalDetails, VehicalDetailsDto.class);
          
          VehicalDetailsResponse response = new VehicalDetailsResponse();
          response.setContent(List.of(VehicalDetailsDto));  
          response.setPageNumber(0);
          response.setPageSize(1);
          response.setTotalElements((long) 1);
          response.setTotalPages(1);
          response.setLastPage(true);
          
          return response;
          
            }


    @Override
    public VehicalDetailsDto updateData(VehicalDetailsDto VehicalDetailsDto, Long vehicalId) {
      
      VehicalDetails DetailsFromdb=vehicalDetailsRepository.findById(vehicalId)
       .orElseThrow(()->new ResourceNotFoundException("VehicalDetails","vehicalId", vehicalId));

       VehicalDetails vehicalDetails=modelMapper.map(VehicalDetailsDto,VehicalDetails.class);
       DetailsFromdb.setVehicalId(vehicalDetails.getVehicalId());
       DetailsFromdb.setVehicalNumber(vehicalDetails.getVehicalNumber());
       DetailsFromdb.setVehicalType(vehicalDetails.getVehicalType());
     
       DetailsFromdb.setDuration(vehicalDetails.getDuration());

       VehicalDetails saveDetails=vehicalDetailsRepository.save(DetailsFromdb);

       return modelMapper.map(saveDetails,VehicalDetailsDto.class);
    }


    @Override
    public VehicalDetailsDto deleteData(Long vehicalId) {
      VehicalDetails DetailsFromdb =vehicalDetailsRepository.findById(vehicalId)
           .orElseThrow(()->new ResourceNotFoundException("VehicalDetails", "vehicalId", vehicalId));

           vehicalDetailsRepository.delete(DetailsFromdb);
           return modelMapper.map(DetailsFromdb, VehicalDetailsDto.class);
    }


    @Override
    public VehicalDetailsDto DurationTime(String vehicalNumber, Long driverId, Integer duration, Long addressId) {
        if (duration == null || duration <= 0) {
                throw new IllegalArgumentException("Duration must be greater than 0");
            }
        
            VehicalDetails vehicalDetails =
                    vehicalDetailsRepository.findByVehicalNumber(vehicalNumber)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "VehicalDetails", "vehicalNumber", vehicalNumber));
        
            DriverDetails driverDetails =
                    driverDetailsRepository.findById(driverId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "DriverDetails", "driverId", driverId));
        
            Address address =
                    addressRepository.findById(addressId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Address", "addressId", addressId));
        
            vehicalDetails.setDriverDetails(driverDetails);
            vehicalDetails.setDuration(duration);
        
            double feePerHour = feeConfig.getFee(vehicalDetails.getVehicalType());
            double totalFee = feePerHour * duration;
            vehicalDetails.setFee(totalFee);
        
            vehicalDetailsRepository.save(vehicalDetails);
        
            boolean activeBookingExists =
                    myBookingRepository.existsByVehicalNumberAndStatus(
                            vehicalNumber, BookingStatus.ACTIVE);
        
            if (!activeBookingExists) {
                MyBooking booking = new MyBooking();
                booking.setVehicalNumber(vehicalNumber);
                booking.setVehicalType(vehicalDetails.getVehicalType().name());
                booking.setDuration(duration);
                booking.setFee(totalFee);
                booking.setAddress(address); // ✅ reuse address
                booking.setStatus(BookingStatus.ACTIVE);
        
                myBookingRepository.save(booking);
            }
        
            return modelMapper.map(vehicalDetails, VehicalDetailsDto.class);
        }


}   