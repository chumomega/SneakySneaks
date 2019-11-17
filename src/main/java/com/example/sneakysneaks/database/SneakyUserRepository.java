package com.example.sneakysneaks.database;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.sneakysneaks.objects.SneakyUser;


@RepositoryRestResource(exported = false)
public interface SneakyUserRepository extends Repository<SneakyUser, Long>{
 
	SneakyUser save(SneakyUser user);
	SneakyUser findByName(String name);
	SneakyUser findByEmail(String email);

}
