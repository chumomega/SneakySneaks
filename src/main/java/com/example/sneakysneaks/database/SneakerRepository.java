package com.example.sneakysneaks.database;

import org.springframework.stereotype.Repository;

import com.example.sneakysneaks.objects.Sneaker;

@Repository
public interface SneakerRepository {
	Iterable<Sneaker> getSneakers();
	Iterable<Sneaker> findBrand(String brand);
	Iterable<Sneaker> findSneaker(int id);
	boolean addSneaker(String brand, String name, int size, double price, String about, String picture);
	boolean removeSneaker(int id);
}
