package com.Online.ParkigManagement.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
public class UserDto {

    private Long id;
   
    private String email;
    private String password;
    private DriverDetailsDto driverDetailsDto;
    private AddressDto addressDto;
    private String role;

      

  
}
