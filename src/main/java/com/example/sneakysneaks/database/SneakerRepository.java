package com.example.sneakysneaks.database;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.sneakysneaks.objects.Sneaker;
import com.example.sneakysneaks.objects.SneakyUser;

//@PreAuthorize("hasRole('SNEAKY_USER')")
@RepositoryRestResource(collectionResourceRel = "sneakers", path = "sneakers")
public interface SneakerRepository extends PagingAndSortingRepository<Sneaker, Long>{
	
	@Override
	@PreAuthorize("#sneaker?.user == null or #sneaker?.user?.name == authentication?.name")
	Sneaker save(@Param("sneaker") Sneaker sneaker);
	
	@Override
	@PreAuthorize("@sneakerRepository.findById(#product_number).get().user.name == authentication?.name")
	void deleteById(@Param("product_number")Long product_number);

	@Override
	@PreAuthorize("#sneaker?.user?.name == authentication?.name")
	void delete(@Param("sneaker") Sneaker sneaker);

	@PreAuthorize("#user?.name == authentication?.name")
	Iterable<Sneaker> findByUser(@Param("user") SneakyUser user);

	Iterable<Sneaker> findByBrand(@Param("brand") String brand);
	Iterable<Sneaker> findByName(@Param("name") String name);
	Iterable<Sneaker> findBySize(@Param("size") Integer size);

}
