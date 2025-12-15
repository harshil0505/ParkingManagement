package com.Online.ParkigManagement.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Online.ParkigManagement.Payload.AddressDto;
import com.Online.ParkigManagement.Repository.AddressRepository;
import com.Online.ParkigManagement.Repository.FavouriteAddressRepository;
import com.Online.ParkigManagement.Repository.UserRepository;
import com.Online.ParkigManagement.Security.ServiceSecurity.UserDetaileImpl;
import com.Online.ParkigManagement.excepasion.ResourceNotFoundException;
import com.Online.ParkigManagement.model.Address;

import com.Online.ParkigManagement.model.FavouriteAddress;
import com.Online.ParkigManagement.model.User;

import jakarta.transaction.Transactional;

@Service

@Transactional
public class FavouriteAddressServiceImpl implements FavouriteAddressService {

    @Autowired
    private FavouriteAddressRepository favouriteRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    

@PreAuthorize("hasRole('DRIVER')")
    @Override
    public AddressDto MyFavourite(Long addressId) {
       

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        UserDetaileImpl userDetails =
                (UserDetaileImpl) authentication.getPrincipal();

        User user = userRepository.findById(userDetails.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", userDetails.getId()));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Address", "id", addressId));

        // remove old favourite
        favouriteRepository.deleteByUserId(user.getId());

        FavouriteAddress favourite = new FavouriteAddress();
        favourite.setUser(user);
        favourite.setAddress(address);
        favourite.setMyfavourite(true);

        AddressDto dto = modelMapper.map(address, AddressDto.class);
    
     
        dto.setMyfavourite(true);
    
        return dto;
    }

   


    @Override
    public List<Address> search(String q) {
        
        String[] keywords = q.toLowerCase().trim().split("\\s+");
    
        Specification<Address> spec = Specification.where((root, query, cb) -> cb.conjunction());

        for (String keyword : keywords) {
    
            String pattern = "%" + keyword + "%";
    
            spec = spec.or((root, query, cb) -> cb.or(
                    cb.like(cb.lower(root.get("street")), pattern),
                    cb.like(cb.lower(root.get("city")), pattern),
                    cb.like(cb.lower(root.get("state")), pattern),
                    cb.like(cb.lower(root.get("pincode")), pattern)
            ));
          
        }
    
        return addressRepository.findAll(spec);
    }





    
    @Override
    public AddressDto getAllFavouriteAddress() {
    
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
    
        UserDetaileImpl userDetails =
                (UserDetaileImpl) authentication.getPrincipal();
    
        User user = userRepository.findById(userDetails.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", userDetails.getId()));
    
        FavouriteAddress favourite =
                favouriteRepository.findByUserId(user.getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Favourite", "userId", user.getId()));
    
        Address address = favourite.getAddress();

        AddressDto dto = modelMapper.map(address, AddressDto.class);
    
     
        dto.setMyfavourite(true);
    
        return dto;
    }
    

    }


