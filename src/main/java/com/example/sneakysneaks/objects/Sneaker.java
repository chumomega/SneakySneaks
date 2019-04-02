package com.example.sneakysneaks.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;


@Data
@Entity
public class Sneaker {
	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	private Long product_number;
	private String brand;
	private String name;
	private int size; 
	private double price;
	private String about;
	private String picture;
	
	private @ManyToOne SneakyUser user;
	
	public Sneaker() {
		
	}
    
    public Sneaker(String brand, String name, int size, double price, String about, String picture, SneakyUser user) {
    	this.setBrand(brand);
    	this.setName(name);
    	this.setSize(size);
    	this.setPrice(price);
    	this.setAbout(about);
    	this.setPicture(picture);
    	this.user = user;
    }
    
    public void setUser(SneakyUser user) {
		this.user = user;
	}
    
    public SneakyUser getUser() {
		return this.user;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
    

}
