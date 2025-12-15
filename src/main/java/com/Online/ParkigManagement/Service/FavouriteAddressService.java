package com.Online.ParkigManagement.Service;

import java.util.List;

import com.Online.ParkigManagement.Payload.AddressDto;
import com.Online.ParkigManagement.model.Address;

public interface FavouriteAddressService {

    

    List<Address> search(String q);

    AddressDto getAllFavouriteAddress();

    AddressDto MyFavourite(Long addressId);

  

  

}
