package com.example.sneakysneaks.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.sneakysneaks.model.Sneaker;
import com.example.sneakysneaks.model.SneakyUser;

@RepositoryRestResource(collectionResourceRel = "sneakers", path = "sneakers")
public interface SneakerRepository extends JpaRepository<Sneaker, Long> {
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

	@Query("SELECT s FROM Sneaker s WHERE s.user.name = :name")
	Page<Sneaker> findByOwner(@Param("name") String name, Pageable pageable);

	Iterable<Sneaker> findByBrand(@Param("brand") String brand);
	Iterable<Sneaker> findByName(@Param("name") String name);
	Iterable<Sneaker> findBySize(@Param("size") Integer size);

}
