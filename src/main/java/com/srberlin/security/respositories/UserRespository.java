package com.srberlin.security.respositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srberlin.security.models.UserModel;

public interface UserRespository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByEmail(String email);
}
