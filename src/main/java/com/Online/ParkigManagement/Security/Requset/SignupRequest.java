package com.Online.ParkigManagement.Security.Requset;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    private String email;
    private String password;
    private Set<String> roles;
}
