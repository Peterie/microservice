package com.callaars.peter.userservice.repository;

import com.callaars.peter.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

//    @Query("FROM User u WHERE u.birthDate <= :date")
//    List<User> getUsersOlderThan(String date);
}
