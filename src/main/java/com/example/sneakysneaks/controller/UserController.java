package com.example.sneakysneaks.controller;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.sneakysneaks.database.SneakyUserRepository;
import com.example.sneakysneaks.objects.SneakyUser;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	SneakyUserRepository userRepository;

    @ApiOperation(value= "register new user")
	@RequestMapping(path="/sneakyuser", method = RequestMethod.POST, consumes= "application/json")
    public SneakyUser registerUser(String userName, String description, String email, String phoneNumber, String password) {
        try {
            return userRepository.save(new SneakyUser(userName, description, email, phoneNumber, password, "SNEAKY_USER"));
        } catch(IllegalArgumentException e) {
            return null;
        }
    }

    @ApiOperation(value= "get existing user")
	@RequestMapping(path="/sneakyuser", method = RequestMethod.GET, consumes= "application/json", produces = "application/json")
    public SneakyUser getUser(String userName) {
        SneakyUser potentialUser = userRepository.findByName(userName);
        return potentialUser;
    }
}
