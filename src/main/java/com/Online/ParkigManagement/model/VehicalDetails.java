package com.Online.ParkigManagement.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "vehical_details")
public class VehicalDetails {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long vehicalId;
    
    @Column(unique = true)
    private String vehicalNumber;
    
   @Enumerated(jakarta.persistence.EnumType.STRING)
    private VehicalType vehicalType;

    @NonNull
    private Integer  duration;
  
    @NonNull
    private Double fee;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "driver_id")
    private DriverDetails driverDetails;

   
}