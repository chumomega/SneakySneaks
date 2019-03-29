package com.example.sneakysneaks.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.sneakysneaks.objects.SneakyUser;


@RepositoryRestResource(exported = false)
public interface SneakyUserRepository extends CrudRepository<SneakyUser, Long>{
 
	SneakyUser save(SneakyUser user);
	SneakyUser findByfirstName(String firstName);
	

}
