package com.Online.ParkigManagement.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String parkingName;
    private String street;
    private String city;
    private String state;
    private String pincode;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private boolean AdminAddress;
        private boolean myfavourite;
    
        public boolean isMyfavourite() {
            return myfavourite;
        }
    
      
    
    
        public void setMyfavourite(boolean b) {
            this.myfavourite = myfavourite;
    }

}
