package com.example.sneakysneaks.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;

import com.example.sneakysneaks.objects.SneakyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.sneakysneaks.database.SneakerRepository;
import com.example.sneakysneaks.database.SneakyUserRepository;
import com.example.sneakysneaks.objects.Sneaker;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "This is the controller for the Sneakysneaks application")
@RequestMapping("/api")
public class SneakysneaksController {
	
	@Autowired
	private SneakerRepository sneakerRepo;

	@Autowired
	private SneakyUserRepository userRepo;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index() {		
		return "Welcome to the Sneakysneaks App, the time right now is " + new Date();
	}
	
	
	@ApiOperation(value= "returns a list of shoes", notes = " enter the brand to do a search for the shoe")
	@RequestMapping(path="/sneakers", method = RequestMethod.GET, produces = "application/hal+json")
	public ArrayList<Sneaker> getSneakers(){
		return (ArrayList<Sneaker>) sneakerRepo.findAll();
	}
	
	@ApiOperation(value= "add sneaker", notes = "add ALL parameters for a sneaker")
	@RequestMapping(path="/sneakers", method = RequestMethod.POST, consumes= "application/json", produces = "application/json")
	public boolean addSneaker(String brand, String name, int size, double price, String about, String picture){
		SneakyUser user = getAuthenticatedUser();
		sneakerRepo.save(new Sneaker(brand, name, size, price, about, picture, user));
		return false;
	}
	@ApiOperation(value= "update sneaker", notes = "add ALL parameters to update a sneaker")
	@RequestMapping(path="/sneakers", method = RequestMethod.PUT, consumes= "application/json", produces = "application/json")
	public boolean updateSneaker(Long product_number, String brand, String name, int size, double price, String about, String picture){
		try{
			Sneaker sneaker = sneakerRepo.findById(product_number).get();
			sneaker.setAbout(about);
			sneaker.setBrand(brand);
			sneaker.setName(name);
			sneaker.setPicture(picture);
			sneaker.setPrice(price);
			sneaker.setSize(size);
			sneakerRepo.save(sneaker);
			return true;
		}
		catch(NoSuchElementException e) {
			return false;
		}
	}

	@ApiOperation(value= "remove sneaker", notes = "add id for the sneaker you want to delete ")
	@RequestMapping(path="/sneakers", method = RequestMethod.DELETE, produces = "application/json")
	public void removeSneaker(Long product_number){
		sneakerRepo.deleteById(product_number);
	}
	
	@ApiOperation(value= "returns a list of shoes", notes = " enter the brand to do a search for the shoe")
	@RequestMapping(path="/findbrand", method = RequestMethod.GET, consumes= "application/json", produces = "application/json")
	public ArrayList<Sneaker> findSneakerByBrand(String brand){
		return null;
	}

	private SneakyUser getAuthenticatedUser() {
		return userRepo.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
	}
	
}


















