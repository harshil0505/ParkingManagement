package com.Online.ParkigManagement.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.Online.ParkigManagement.Config.RoleConstants;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    public User(String string, String encode) {
        this.email = string;
        this.password = encode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;
    
    @NotBlank
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Size(min = 6, max =100, message = "Password must be between 6 and 50 characters") 
    @Column(name = "password")
    private String password;

   
    @OneToOne(cascade = CascadeType.ALL)
      @JoinColumn(name = "driver_id", referencedColumnName = "driverId")
      private DriverDetails driverDetails;

      
    @ManyToMany(fetch = FetchType.EAGER)
   
      @JoinTable(
          name = "user_roles",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id")
      )
      private Set<Role> roles = new HashSet<>();

      @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true )
      private List<Address> addresses=new ArrayList<>();

      public void addRole(Role role) {
        this.roles.add(role);
      }

      public AppRole getAppRole() {
        if (roles.stream().anyMatch(role -> role.getRoleName().equals(RoleConstants.ROLE_ADMIN))) {
            return AppRole.ROLE_ADMIN;
        } else if (roles.stream().anyMatch(role -> role.getRoleName().equals(RoleConstants.ROLE_DRIVER))) {
            return AppRole.ROLE_DRIVER;
         // Default role if none match
        }
        return AppRole.ROLE_DRIVER;
      }

   


   
}
