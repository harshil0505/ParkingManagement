package com.Online.ParkigManagement.Service;



import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Online.ParkigManagement.Payload.AddressDto;
import com.Online.ParkigManagement.Repository.AddressRepository;
import com.Online.ParkigManagement.Repository.UserRepository;
import com.Online.ParkigManagement.Security.ServiceSecurity.UserDetaileImpl;
import com.Online.ParkigManagement.excepasion.ResourceNotFoundException;
import com.Online.ParkigManagement.model.Address;
import com.Online.ParkigManagement.model.AppRole;
import com.Online.ParkigManagement.model.User;



@Service
public class AddressServiceImpl implements AddressServices {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;


    
@Override
public AddressDto createAddress(AddressDto addressDto) {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    UserDetaileImpl userDetails =
            (UserDetaileImpl) authentication.getPrincipal();

    User admin = userRepository.findById(userDetails.getId())
            .orElseThrow(() ->
                    new ResourceNotFoundException("Admin", "id", userDetails.getId()));

    
    if (admin.getAppRole() != AppRole.ROLE_ADMIN) {
        throw new RuntimeException("Only admin can create address");
    }
    

    Address address = modelMapper.map(addressDto, Address.class);
    address.setAdminAddress(true);
    address.setUser(admin);
    address.setMyfavourite(false);
  

    Address savedAddress = addressRepository.save(address);

    return modelMapper.map(savedAddress, AddressDto.class);
}






    @Override
    public AddressDto Updateaddress(AddressDto addressDto, Long adminId) {
      
        Address address=addressRepository.findById(addressDto.getAddressId())
       .orElseThrow(()->new ResourceNotFoundException("Address","addressId",addressDto.getAddressId()));

        if(address.isAdminAddress()){
            throw new RuntimeException("Only admin can update address");
        }

        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setPincode(addressDto.getPincode());
        address.setStreet(addressDto.getStreet());

        Address Updateaddress=addressRepository.save(address);
        return modelMapper.map(Updateaddress, AddressDto.class);



        
    }

    @Override
    public AddressDto Deleteaddress(Long addressId, Long adminId) {
        Address address=addressRepository.findById(addressId)
        .orElseThrow(()->new ResourceNotFoundException("Address","addressId",addressId));

        if(address.isAdminAddress()){
            throw new RuntimeException("Only admin can delete address");
        }

        addressRepository.delete(address);
        return modelMapper.map(address, AddressDto.class);
    }



  


    

}
  

   

    
        
 

 
    


