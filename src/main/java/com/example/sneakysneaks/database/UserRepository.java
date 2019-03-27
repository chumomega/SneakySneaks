package com.example.sneakysneaks.database;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.sneakysneaks.objects.User;



public interface UserRepository extends CrudRepository<User, Long>{
	List<User> findByLastName(String lastName);
	

}
