package com.example.sneakysneaks.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.sneakysneaks.database.UserRepository;
import com.example.sneakysneaks.objects.User;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {
	@Autowired
	UserRepository repo;
	
    @ApiOperation(value= "check if database is up")
   	@RequestMapping(path="/api/user", method = RequestMethod.GET)
   	public String getUser() {
    	return "User";
    	
    }

    @ApiOperation(value= "check if database is up")
	@RequestMapping(path="/api/logout", method = RequestMethod.GET)
    public String logoutUser() {
    	return "Logout User";
    	
    }
    @ApiOperation(value= "get list of users")
	@RequestMapping(path="/api/users", method = RequestMethod.GET)
    public ArrayList<User> getusers(){
    	return (ArrayList<User>) repo.findAll();
    	
    }

    @ApiOperation(value= "check if database is up")
	@RequestMapping(path="/api/signup", method = RequestMethod.POST, consumes= "application/json")
    public String signupUser(String firstName, String lastName, String description, String email, String phoneNumber,String password) {
    	repo.save(new User(firstName, lastName, description, email, phoneNumber, password));
    	
    	return "Signup User";
    	
    }
	
    
}
