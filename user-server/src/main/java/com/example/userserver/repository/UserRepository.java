package com.example.userserver.repository;

import com.example.userserver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByPhoneNumber(String phoneNumber);

}