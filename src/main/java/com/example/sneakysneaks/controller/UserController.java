package com.example.sneakysneaks.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {
	
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
    
    @ApiOperation(value= "check if database is up")
	@RequestMapping(path="/api/signup", method = RequestMethod.POST, consumes= "application/json")
    public String signupUser(String email, String password, String phoneNumber, String displayName) {
    	CreateRequest request = new CreateRequest()
    		    .setEmail(email)
    		    .setEmailVerified(false)
    		    .setPassword(password)
    		    .setPhoneNumber(phoneNumber)
    		    .setDisplayName(displayName)
    		    .setDisabled(false);

		UserRecord userRecord;
		try {
			userRecord = FirebaseAuth.getInstance().createUser(request);
    		System.out.println("Successfully created new user: " + userRecord.getUid());
		} 
		catch (FirebaseAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Was not able to create the user");
		}
    	return "Signup User";
    	
    }
	
    
}
