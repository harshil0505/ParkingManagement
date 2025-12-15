package com.Online.ParkigManagement.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteAddressDto {

    private Long id;
    private UserDto userDto;
    private AddressDto addressDto;
}
