package com.sis.rosario.flight_services.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import com.sis.rosario.flight_services.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    Optional<User> findByUsername(String username);

	boolean existsByUsername(String username);
    
}