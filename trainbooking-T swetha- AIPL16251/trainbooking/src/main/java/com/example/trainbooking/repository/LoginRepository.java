package com.example.trainbooking.repository;

import com.example.trainbooking.entity.Customer;
import com.example.trainbooking.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Customer, Long> {
    Optional<Login> findByEmail(String email);
}