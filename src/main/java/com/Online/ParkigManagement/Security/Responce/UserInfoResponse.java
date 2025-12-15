package com.Online.ParkigManagement.Security.Responce;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
       
    
    private Long id;
    private String email;
    private String password;
    private List<String> roles;
    private String token;
}
 