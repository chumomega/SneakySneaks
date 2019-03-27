package com.example.sneakysneaks.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.sneakysneaks.objects.User;


@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Long>{
 
	User save(User user);

	User findByfirstName(String firstName);
	

}
