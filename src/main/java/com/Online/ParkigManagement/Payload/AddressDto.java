package com.Online.ParkigManagement.Payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto {

   private Long addressId;
    private String parkingName;
    private String street;
    private String city;
    private String state;
    private String pincode;
    private boolean Myfavourite;
    private UserDto userDto;

    
    
    private boolean AdminAddress;



    



 
}
 