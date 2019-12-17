package com.example.sneakysneaks.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.sneakysneaks.model.SneakyUser;



//@RepositoryRestResource(exported = false)
@Repository
public interface SneakyUserRepository extends CrudRepository<SneakyUser, Long> {

	/**
	 * Runs an SQL query and returns a SneakyUser if they exist in the database
	 * 
	 * @param name
	 * @param password
	 * @return SneakyUser 
	 */
//	@Query("FROM sneaky_user u WHERE u.name = ?1 and u.password = ?2")
//	public SneakyUser login(String name, String password);
	
	@SuppressWarnings("unchecked")
	SneakyUser save(SneakyUser user);
	SneakyUser findByName(String name);
	SneakyUser findByEmail(String email);
	
//	@PreAuthorize("@sneakerRepository.findById(#product_number).get().user.name == authentication?.name")
//	@PreAuthorize("@userRepository.findById(#user?.name)"
//			"#user?.name == null or #sneaker?.user?.name == authentication?.name"
	
	
	

}
