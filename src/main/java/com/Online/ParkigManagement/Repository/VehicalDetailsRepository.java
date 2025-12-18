package com.Online.ParkigManagement.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.Online.ParkigManagement.model.VehicalDetails;
import com.Online.ParkigManagement.model.VehicalType;

public interface VehicalDetailsRepository extends JpaRepository<VehicalDetails, Long> {

  Optional<VehicalDetails> findByVehicalNumber(String vehicalNumber);

  Optional<VehicalDetails> findFirstByVehicalNumberOrderByVehicalIdDesc(String vehicalNumber);

  List<VehicalDetails> findByVehicalType(VehicalType vehicalType);
}
