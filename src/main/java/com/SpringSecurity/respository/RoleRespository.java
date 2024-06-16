package com.SpringSecurity.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SpringSecurity.Models.Roles;

@Repository
public interface RoleRespository extends JpaRepository<Roles, Integer>{
	Optional<Roles> findByRole(@Param("role") String role);
}
