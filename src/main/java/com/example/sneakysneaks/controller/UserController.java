package com.example.sneakysneaks.controller;

import com.example.sneakysneaks.service.SneakyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.sneakysneaks.repository.SneakyUserRepository;
import com.example.sneakysneaks.model.SneakyUser;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class UserController {
	
//	@Bean("encoder")
//	public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder();
//	}

	@Autowired
	SneakyUserRepository userRepository;

	@Autowired
	SneakyUserService sneakyUserService; // adding intermediary service layer

	@ApiOperation(value = "register new user")
	@RequestMapping(path = "/sneakyuser", method = RequestMethod.POST, consumes = "application/json")
	public SneakyUser registerUser(String userName, String description, String email, String phoneNumber,
			String password) {
		try {
			return userRepository
					.save(new SneakyUser(userName, description, email, phoneNumber, password, "SNEAKY_USER"));
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@ApiOperation(value = "get existing user")
	@RequestMapping(path = "/sneakyuser", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public SneakyUser getUser(String userName) {
		SneakyUser potentialUser = userRepository.findByName(userName);
		return potentialUser;
	}

	/**
	 * Allows user sign-up by passing in SneakyUser object
	 * 
	 * @param SneakerUSer to sign-up with
	 */
	@PostMapping("/save")
	public SneakyUser saveUser(@RequestBody SneakyUser user) {
		return sneakyUserService.saveUser(user);
	}

	/**
	 * Allows user log-up by passing in username and password
	 * 
	 * @param String username
	 * @param String password
	 */
//	@PostMapping("/login")
//	public SneakyUser login(@PathVariable String name, @PathVariable String password) {
//		return sneakyUserService.login(name, password);
//	}
}
