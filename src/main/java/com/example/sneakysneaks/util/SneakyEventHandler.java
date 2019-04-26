package com.example.sneakysneaks.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.sneakysneaks.database.SneakyUserRepository;
import com.example.sneakysneaks.objects.Sneaker;
import com.example.sneakysneaks.objects.SneakyUser;

import static com.example.sneakysneaks.util.WebSocketConfiguration.*;

@Component
@RepositoryEventHandler(Sneaker.class)
public class SneakyEventHandler {
	private final SneakyUserRepository userRepository;
	
	private final SimpMessagingTemplate websocket;

	private final EntityLinks entityLinks;
	
	
	@Autowired
	public SneakyEventHandler(SneakyUserRepository userRepository, SimpMessagingTemplate websocket, EntityLinks entityLinks) {
		this.websocket = websocket;
		this.entityLinks = entityLinks;
		this.userRepository = userRepository;
	}

	@HandleBeforeCreate
	@HandleBeforeSave
	public void applyUserInformationUsingSecurityContext(Sneaker sneaker) {

		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		SneakyUser user = this.userRepository.findByfirstName(name);
		if (user == null) {
			SneakyUser newUser = new SneakyUser();
			newUser.setFirstName(name);
			newUser.setRoles(new String[]{"SNEAKER_MANAGER"});
			user = this.userRepository.save(newUser);
		}
		sneaker.setUser(user);
	}
	
	@HandleAfterCreate
	public void newsneaker(Sneaker sneaker) {
		this.websocket.convertAndSend(
				MESSAGE_PREFIX + "/newSneaker", getPath(sneaker));
	}

	@HandleAfterDelete
	public void deleteSneaker(Sneaker sneaker) {
		this.websocket.convertAndSend(
				MESSAGE_PREFIX + "/deleteSneaker", getPath(sneaker));
	}

	@HandleAfterSave
	public void updateSneaker(Sneaker sneaker) {
		this.websocket.convertAndSend(
				MESSAGE_PREFIX + "/updateSneaker", getPath(sneaker));
	}
	
	private String getPath(Sneaker sneaker) {
		return this.entityLinks.linkForSingleResource(sneaker.getClass(),
				sneaker.getProductNumber()).toUri().getPath();
	}

}
