package com.SpringSecurity.service;

import java.sql.Date;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.SpringSecurity.DTO.JwtResponseDTO;
import com.SpringSecurity.DTO.LoginDTO;
import com.SpringSecurity.DTO.UserDTO;
import com.SpringSecurity.Models.Roles;
import com.SpringSecurity.Models.Users;
import com.SpringSecurity.config.JwtService;
import com.SpringSecurity.respository.RoleRespository;
import com.SpringSecurity.respository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRespository roleRespository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationProvider authenticationProvider;
	@Autowired
	private JwtService jwtService;

	public ResponseEntity<?> resgister(UserDTO userDTO) {
		try {

			if (validateRegister(userDTO)) {
				if (this.userRepository.existsByUserName(userDTO.getEmail())) {
					return new ResponseEntity<String>("User already exists in database", HttpStatus.BAD_REQUEST);
				} else {
					this.userRepository.save(getUserDetails(userDTO));
					return new ResponseEntity<String>("user registered successfully", HttpStatus.OK);
				}
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
		return new ResponseEntity<String>("Please enter all required data", HttpStatus.BAD_REQUEST);
	}

	private Users getUserDetails(UserDTO userDTO) {
		Date date = new Date(System.currentTimeMillis());
		Users users = new Users();
		users.setName(userDTO.getName());
		users.setUserName(userDTO.getUserName());
		users.setAddress(userDTO.getAddress());
		users.setEmail(userDTO.getEmail());
		users.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		users.setCreatedDate(date);
		Roles roles = this.roleRespository.findByRole("USER").get();
		users.setRoles(Collections.singletonList(roles));
		users.setPhoneNo(userDTO.getPhoneNo());

		return users;
	}

	private boolean validateRegister(UserDTO userDTO) {

		if (userDTO.getUserName().equals(null) || userDTO.getUserName().trim().isEmpty())
			return false;
		if (userDTO.getName().equals(null) || userDTO.getName().isEmpty())
			return false;
		if (userDTO.getPhoneNo().equals(null) || userDTO.getPhoneNo().isEmpty())
			return false;
		if (userDTO.getEmail().equals(null) || userDTO.getEmail().isEmpty() || !userDTO.getEmail().contains("@"))
			return false;
		if (userDTO.getPassword().equals(null) || userDTO.getPassword().isEmpty())
			return false;
		if (userDTO.getPhoneNo().equals(null) || userDTO.getPhoneNo().isEmpty())
			return false;
		return true;
	}

	public ResponseEntity<?> login(LoginDTO loginDTO) {
		try {
			Authentication authentication = authenticationProvider
					.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

			if (authentication.isAuthenticated()) {
				String token = jwtService.GenerateToken(loginDTO.getEmail());
				JwtResponseDTO jwtResponse = JwtResponseDTO.builder().accessToken(token).build();
				return ResponseEntity.ok(jwtResponse);
			}

			return ResponseEntity.badRequest().body("Invalid Username or Password");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
