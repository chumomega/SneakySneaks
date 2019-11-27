package com.example.sneakysneaks.database;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.sneakysneaks.objects.SneakyUser;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;


@RepositoryRestResource(exported = false)
public interface SneakyUserRepository extends Repository<SneakyUser, Long>{
//	@PreAuthorize("@sneakerRepository.findById(#product_number).get().user.name == authentication?.name")
//	@PreAuthorize("@userRepository.findById(#user?.name)"
//			"#user?.name == null or #sneaker?.user?.name == authentication?.name")
	SneakyUser save(SneakyUser user);
	SneakyUser findByName(String name);
	SneakyUser findByEmail(String email);

}
