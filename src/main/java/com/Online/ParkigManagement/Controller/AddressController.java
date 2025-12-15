package com.Online.ParkigManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Online.ParkigManagement.Payload.AddressDto;
import com.Online.ParkigManagement.Service.AddressServices;


@RestController
@RequestMapping("/api/admin")
public class AddressController {
   
    

    @Autowired
    private AddressServices addressServices;

    @PostMapping("/admin/createaddress")
    public ResponseEntity<AddressDto> Createaddress(@RequestBody AddressDto addressDto){
        AddressDto addressDto2 =addressServices.createAddress(addressDto);
        return new ResponseEntity<>(addressDto2, HttpStatus.CREATED);
    }

    
    
    @PostMapping("/admin/{AdminId}/updateaddress")
    public ResponseEntity<AddressDto> Updateaddress(@RequestBody AddressDto addressDto,@PathVariable Long AdminId){
        AddressDto addressDto2 =addressServices.Updateaddress(addressDto, AdminId);
        return new ResponseEntity<>(addressDto2, HttpStatus.OK);
    }

    @GetMapping("/admin/{addressId}/{AdminId}/deleteaddress")
    public ResponseEntity<AddressDto> Deleteaddress(@PathVariable Long addressId,@PathVariable Long AdminId){
        AddressDto addressDto2=addressServices.Deleteaddress(addressId, AdminId);
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
        
        
    
            }