package com.Online.ParkigManagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor

public class Role {


 @Id   
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id") 
 private Integer roleId;


    @Column(name = "role_name", nullable = false)
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;

    }

}