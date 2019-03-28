package com.example.sneakysneaks.database;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.sneakysneaks.objects.Sneaker;

@Repository
public interface SneakerRepository extends PagingAndSortingRepository<Sneaker, Long>{
	Iterable<Sneaker> findAllByBrand(String brand);
	void deleteById(Long product_number);
	Iterable<Sneaker> findAll();
	
}
