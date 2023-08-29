package com.pivo.hotelo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.pivo.hotelo.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	UserDetails findByEmail(String email);
}
