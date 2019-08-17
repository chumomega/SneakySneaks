package com.example.sneakysneaks.objects;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private @Version @JsonIgnore Long version;
	
	//private @ManyToOne SneakyUser user;
	
	public Sneaker() {
		
	}
    
    public Sneaker(String brand, String name, int size, double price, String about, String picture) {
    	//, SneakyUser user
    	// removed from parameter list
    	this.setBrand(brand);
    	this.setName(name);
    	this.setSize(size);
    	this.setPrice(price);
    	this.setAbout(about);
    	this.setPicture(picture);
    	//this.user = user;
    }
    
//    public void setUser(SneakyUser user) {
//		this.user = user;
//	}
//    
//    public SneakyUser getUser() {
//		return this.user;
//	}
    
    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Sneaker sneaker = (Sneaker) o;
		return Objects.equals(product_number, sneaker.product_number) &&
			Objects.equals(name, sneaker.name) &&
			Objects.equals(size, sneaker.size) &&
			Objects.equals(price, sneaker.price) &&
			Objects.equals(about, sneaker.about) &&
			Objects.equals(picture, sneaker.picture) &&
			Objects.equals(version, sneaker.version);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, size, price, about, picture, version);
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

	public Long getProductNumber() {
		return this.product_number;
	}
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	
	@Override
	public String toString() {
		return "Sneaker{" +
			"product_number=" + product_number +
			", name='" + name + '\'' +
			", price='" + price + '\'' +
			", about='" + about + '\'' +
			", picture=" + picture + '\'' +
			", version=" + version +
			'}';
	
	}
    

}
