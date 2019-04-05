package com.example.sneakysneaks.database;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.sneakysneaks.objects.Sneaker;
import com.example.sneakysneaks.objects.SneakyUser;

//TODO - I removed this in order to keep the auth that spring provided while also allowing users to view sneakers without errors
// It is definitely a work around that could hurt the project if not fixed
// @PreAuthorize("hasRole('SNEAKER_MANAGER')")
public interface SneakerRepository extends PagingAndSortingRepository<Sneaker, Long>{
	
	@Override
	@PreAuthorize("#sneaker?.user == null or #sneaker?.user?.firstName == authentication?.name")
	Sneaker save(@Param("sneaker") Sneaker sneaker);
	
	@Override
	@PreAuthorize("@sneakerRepository.findById(#id)?.user?.firstName == authentication?.name")
	void deleteById(@Param("id")Long product_number);
	
	@Override
	@PreAuthorize("#sneaker?.user?.firstName == authentication?.name")
	void delete(@Param("sneaker") Sneaker sneaker);
	
	@PreAuthorize("#user?.firstName == authentication?.name")
	Iterable<Sneaker> findAllByUser(@Param("user") SneakyUser user);
	
	
}
