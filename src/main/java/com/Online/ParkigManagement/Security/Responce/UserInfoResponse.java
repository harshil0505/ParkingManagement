package com.Online.ParkigManagement.Security.Responce;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserInfoResponse {
       
    
    private Long id;
    private String email;

    private List<String> roles;
    private String token;

   public UserInfoResponse(Long id, String email, List<String> roles) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.token = null;
   }
}
 