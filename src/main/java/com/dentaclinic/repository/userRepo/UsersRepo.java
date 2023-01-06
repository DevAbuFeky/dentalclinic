package com.dentaclinic.repository.userRepo;

import com.dentaclinic.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

    Users findByUserName(String userName);

//    Optional<Users> findByUserNameAndPassword(String userName, String password);
    Users findByEmail(String userEmail);
}
