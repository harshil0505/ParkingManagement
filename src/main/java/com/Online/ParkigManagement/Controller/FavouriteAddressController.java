package com.Online.ParkigManagement.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Online.ParkigManagement.model.Address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Online.ParkigManagement.Payload.AddressDto;
import com.Online.ParkigManagement.Service.FavouriteAddressService;


@RestController
@RequestMapping("/api/driver")
public class FavouriteAddressController {

    
    @Autowired
    private FavouriteAddressService favouriteAddressService;

    @PatchMapping("/address/{addressId}/myfavourite")
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<AddressDto> MyFavourite(@PathVariable Long addressId){
   
        AddressDto addressDto2 =favouriteAddressService.MyFavourite(addressId);
        return new ResponseEntity<>(addressDto2, HttpStatus.OK);
    }

    @PostMapping("/search")
    public List<Address> search(@RequestParam String q) {
    return (List<Address>)favouriteAddressService.search(q);
    }

    @GetMapping("/alladdress")
    public ResponseEntity<AddressDto> getAllFavouriteAddress() {
        AddressDto addressDto = favouriteAddressService.getAllFavouriteAddress();
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    
}
}