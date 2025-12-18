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
public class DurationRequestDto {
    private String vehicalNumber;
    private Long driverId;
    private Integer duration;
    private  Long addressId;
    
    
}
