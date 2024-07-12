package com.SpringSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringSecurity.DTO.LoginDTO;
import com.SpringSecurity.DTO.UserDTO;
import com.SpringSecurity.service.UserService;

@RestController
@RequestMapping("/authenicate")
public class AuthenicateController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	private ResponseEntity<?> register(@RequestBody(required = true) UserDTO userDTO) {
		try {
			return this.userService.resgister(userDTO);

		} catch (Exception e) {
			e.getStackTrace();
		}
		return new ResponseEntity<String>("Something went error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/login")
	private ResponseEntity<?> login(@RequestBody(required=true) LoginDTO loginDTO)
	{
		try
		{
			return this.userService.login(loginDTO);
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		return new ResponseEntity<String>("Something went error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
