package com.SpringSecurity.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SpringSecurity.Models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{

	Optional<Users> findByUserName(@Param("email")String email);
	Boolean  existsByUserName(@Param("email")String email);
}
