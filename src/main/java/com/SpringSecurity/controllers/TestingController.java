package com.SpringSecurity.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing")
public class TestingController {
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/ping")
	public String test() {
		try {
			return "Welcome";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/testadmin")
	public ResponseEntity<String> adminLogin() {
		return ResponseEntity.ok("Welcome ADMIN");
	}

	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/user/testuser")
	public ResponseEntity<String> userLogin() {
		return ResponseEntity.ok("Welcome User");
	}

}
