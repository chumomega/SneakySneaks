package com.example.sneakysneaks.database;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.sneakysneaks.objects.Sneaker;

@PreAuthorize("hasRole('SNEAKER_MANAGER')")
public interface SneakerRepository extends PagingAndSortingRepository<Sneaker, Long>{
	
	@Override
	@PreAuthorize("#sneaker?.user == null or #sneaker?.user?.firstName == authentication?.name")
	Sneaker save(@Param("sneaker") Sneaker sneaker);
	
	@Override
	@PreAuthorize("@sneakerRepository.findById(#id)?.user?.firstName == authentication?.user")
	void deleteById(Long product_number);
	
	@Override
	@PreAuthorize("#sneaker?.user?.firstName == authentication?.user")
	void delete(@Param("sneaker") Sneaker sneaker);

	Iterable<Sneaker> findAll();

	Iterable<Sneaker> findAllByBrand(String brand);
	
	
}
