package com.example.sneakysneaks.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.sneakysneaks.objects.Sneaker;
import com.example.sneakysneaks.objects.User;

@Repository
public interface SneakerRepository extends CrudRepository<Sneaker, Long>{
	Iterable<Sneaker> findAllByBrand(String brand);
	void deleteById(Long product_number);
	Iterable<Sneaker> findAll();
	
}
