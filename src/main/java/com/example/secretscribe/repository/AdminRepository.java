package com.example.secretscribe.repository;

import com.example.secretscribe.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,String> {

    Optional<Admin> findByUsername(String username);
    Optional<Admin> findByUsernameAndPassword(String username,String password);
}
