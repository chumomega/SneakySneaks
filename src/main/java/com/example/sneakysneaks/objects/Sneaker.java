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
	@Id @GeneratedValue
	private Long id;
	private String brand;
	private String name;
	private int size; 
	private double price;
	private String about;
	private String picture;
	private @Version @JsonIgnore Long version;
	
	private @ManyToOne SneakyUser user;
    
    public Sneaker(String brand, String name, int size, double price, String about, String picture, SneakyUser user) {
    	this.brand = brand;
    	this.name = name;
    	this.size = size;
    	this.price = price;
    	this.about = about;
    	this.picture = picture;
    	this.user = user;
    }
    
    public void setUser(SneakyUser user) {
		this.user = user;
	}
    
    public SneakyUser getUser() {
		return this.user;
	}
    
    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Sneaker sneaker = (Sneaker) o;
		return Objects.equals(id, sneaker.id) &&
			Objects.equals(name, sneaker.name) &&
			Objects.equals(size, sneaker.size) &&
			Objects.equals(price, sneaker.price) &&
			Objects.equals(about, sneaker.about) &&
			Objects.equals(version, sneaker.version) &&
			Objects.equals(user, sneaker.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, size, price, about, picture, version, user);
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

	public Long getId() {
		return this.id;
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
			"id=" + id +
			", name='" + name + '\'' +
			", price='" + price + '\'' +
			", about='" + about + '\'' +
			", picture=" + picture + '\'' +
			", version=" + version +
			", user=" + user +
			'}';
	
	}
}
