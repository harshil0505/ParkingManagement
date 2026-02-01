package com.Online.ParkigManagement.Service;

import com.Online.ParkigManagement.Payload.AddressDto;

public interface AddressServices {


  

    AddressDto Updateaddress(AddressDto addressDto, Long adminId);

    AddressDto Deleteaddress(Long addressId, Long adminId);


    AddressDto createAddress(AddressDto addressDto);

  

 

   

}
