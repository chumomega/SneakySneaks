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
	private final SneakyUserRepository sneakerRepository;
	
	@Autowired
	public SneakyEventHandler(SneakyUserRepository sneakerRepository) {
		this.sneakerRepository = sneakerRepository;
	}

	@HandleBeforeCreate
	@HandleBeforeSave
	public void applyUserInformationUsingSecurityContext(Sneaker sneaker) {

		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		SneakyUser user = this.sneakerRepository.findByfirstName(name);
		if (user == null) {
			SneakyUser newUser = new SneakyUser();
			newUser.setFirstName(name);
			newUser.setRoles(new String[]{"SNEAKER_MANAGER"});
			user = this.sneakerRepository.save(newUser);
		}
		sneaker.setUser(user);
	}

}
