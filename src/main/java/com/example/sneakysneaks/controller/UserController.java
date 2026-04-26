package com.example.sneakysneaks.controller;

import java.util.Map;

import com.example.sneakysneaks.service.SneakyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.sneakysneaks.repository.SneakyUserRepository;
import com.example.sneakysneaks.model.SneakyUser;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	SneakyUserRepository userRepository;

	@Autowired
	SneakyUserService sneakyUserService;

	@GetMapping("/me")
	public ResponseEntity<Map<String, String>> me(Authentication auth) {
		if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getName())) {
			return ResponseEntity.status(401).build();
		}
		return ResponseEntity.ok(Map.of("name", auth.getName()));
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@RequestBody SneakyUser user) {
		if (user.getName() == null || user.getName().isBlank()) {
			return ResponseEntity.badRequest().body(Map.of("error", "Username is required"));
		}
		if (userRepository.findByName(user.getName()) != null) {
			return ResponseEntity.status(409).body(Map.of("error", "Username already taken"));
		}
		if (user.getRoles() == null || user.getRoles().length == 0) {
			user.setRoles(new String[]{"SNEAKY_USER"});
		}
		SneakyUser saved = sneakyUserService.saveUser(user);
		return ResponseEntity.ok(Map.of("name", saved.getName()));
	}
}
