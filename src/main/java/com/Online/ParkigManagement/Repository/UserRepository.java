package com.Online.ParkigManagement.Repository;




import org.springframework.data.jpa.repository.JpaRepository;
import com.Online.ParkigManagement.model.User;


public interface UserRepository  extends JpaRepository<User,Long> {

    

    Boolean existsByEmail(String email);

    User findByEmail(String email);

}
