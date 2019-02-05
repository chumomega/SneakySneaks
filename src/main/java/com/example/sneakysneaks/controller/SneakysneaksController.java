package com.example.sneakysneaks.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.sneakysneaks.database.SneakerRepository;
import com.example.sneakysneaks.objects.Sneaker;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "This is the controller for the Sneakysneaks application")
@RequestMapping("/api")
public class SneakysneaksController {
	
	@Autowired
	private SneakerRepository jdbcSneakerRepository;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index() {		
		return "Welcome to the Sneakysneaks App, the time right now is " + new Date();
	}
	
	
	@ApiOperation(value= "check if database is up")
	@RequestMapping(path="/healthCheck", method = RequestMethod.GET, produces = "application/json")
	public boolean healthCheck(){
		if(((ArrayList<Sneaker>) jdbcSneakerRepository.findSneaker(0)).isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	@ApiOperation(value= "returns a list of shoes", notes = " enter the brand to do a search for the shoe")
	@RequestMapping(path="/getSneakers", method = RequestMethod.GET, produces = "application/json")
	public ArrayList<Sneaker> getSneakers(){
		return (ArrayList<Sneaker>) jdbcSneakerRepository.getSneakers();
	}
	
	
	@ApiOperation(value= "returns a list of shoes", notes = " enter the brand to do a search for the shoe")
	@RequestMapping(path="/findbrand", method = RequestMethod.POST, consumes= "application/json", produces = "application/json")
	public ArrayList<Sneaker> findSneakerByBrand(String brand){
		return (ArrayList<Sneaker>) jdbcSneakerRepository.findBrand(brand);
	}
	
	@ApiOperation(value= "add sneaker", notes = "add ALL parameters for a sneaker")
	@RequestMapping(path="/addSneaker", method = RequestMethod.POST, consumes= "application/json", produces = "application/json")
	public boolean addSneaker(String brand, String name, int size, double price){
		return jdbcSneakerRepository.addSneaker(brand, name, size, price);
	}
	
	@ApiOperation(value= "remove sneaker", notes = "add id for the sneaker you want to delete ")
	@RequestMapping(path="/removeSneaker", method = RequestMethod.DELETE, produces = "application/json")
	public boolean removeSneaker(int product_number){
		return jdbcSneakerRepository.removeSneaker(product_number);
	}
	
}


















