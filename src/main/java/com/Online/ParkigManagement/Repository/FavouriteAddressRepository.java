package com.Online.ParkigManagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Online.ParkigManagement.model.FavouriteAddress;

@Repository
public interface FavouriteAddressRepository  extends JpaRepository<FavouriteAddress,Long>{

    Optional<FavouriteAddress> findByUserId(Long userId);

    void deleteByUserId(Long id);

}
