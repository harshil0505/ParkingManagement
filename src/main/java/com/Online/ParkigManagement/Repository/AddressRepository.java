package com.Online.ParkigManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Online.ParkigManagement.model.Address;

public interface AddressRepository  extends JpaRepository<Address, Long> {

    List<Address> findByUserId(Long userId);

    List<Address> findAll(Specification<Address> spec);

}
