package com.example.sneakysneaks.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import static com.example.sneakysneaks.util.WebSocketConfiguration.*;
import com.example.sneakysneaks.model.Sneaker;

@Component
@RepositoryEventHandler(Sneaker.class)
public class EventHandler {
	private final SimpMessagingTemplate websocket;

	private final EntityLinks entityLinks;

	@Autowired
	public EventHandler(SimpMessagingTemplate websocket, EntityLinks entityLinks) {
		this.websocket = websocket;
		this.entityLinks = entityLinks;
	}

	@HandleAfterCreate
	public void newSneaker(Sneaker sneaker) {
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

	/**
	 * Take an {@link Sneaker} and get the URI using Spring Data REST's {@link EntityLinks}.
	 *
	 * @param sneaker
	 */
	private String getPath(Sneaker sneaker) {
		return this.entityLinks.linkForSingleResource(sneaker.getClass(),
				sneaker.getId()).toUri().getPath();
	}


}
