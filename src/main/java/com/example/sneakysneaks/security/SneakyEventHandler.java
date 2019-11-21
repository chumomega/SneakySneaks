package com.example.sneakysneaks.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.sneakysneaks.database.SneakyUserRepository;
import com.example.sneakysneaks.objects.Sneaker;
import com.example.sneakysneaks.objects.SneakyUser;

@Component
@RepositoryEventHandler(Sneaker.class)
public class SneakyEventHandler {
	private final SneakyUserRepository userRepository;
	
	@Autowired
	public SneakyEventHandler(SneakyUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	//TODO - remove this code and depend on the DBAs or Security Ops team to properly maintain the user data store
	@HandleBeforeCreate
	@HandleBeforeSave
	public void applyUserInformationUsingSecurityContext(Sneaker sneaker) {

		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		SneakyUser user = this.userRepository.findByName(name);
		if (user == null) {
			SneakyUser newUser = new SneakyUser();
			newUser.setName(name);
			newUser.setRoles(new String[]{"SNEAKY_USER"});
			user = this.userRepository.save(newUser);
		}
		sneaker.setUser(user);
	}


}
