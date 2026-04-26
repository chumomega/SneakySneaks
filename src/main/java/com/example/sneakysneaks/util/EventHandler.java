package com.example.sneakysneaks.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import static com.example.sneakysneaks.util.WebSocketConfiguration.*;
import com.example.sneakysneaks.model.Sneaker;

@Component
@RepositoryEventHandler(Sneaker.class)
public class EventHandler {
	private final SimpMessagingTemplate websocket;

	@Autowired
	public EventHandler(SimpMessagingTemplate websocket) {
		this.websocket = websocket;
	}

	@HandleAfterCreate
	public void newSneaker(Sneaker sneaker) {
		this.websocket.convertAndSend(MESSAGE_PREFIX + "/newSneaker", getPath(sneaker));
	}

	@HandleAfterDelete
	public void deleteSneaker(Sneaker sneaker) {
		this.websocket.convertAndSend(MESSAGE_PREFIX + "/deleteSneaker", getPath(sneaker));
	}

	@HandleAfterSave
	public void updateSneaker(Sneaker sneaker) {
		this.websocket.convertAndSend(MESSAGE_PREFIX + "/updateSneaker", getPath(sneaker));
	}

	private String getPath(Sneaker sneaker) {
		return "/api/sneakers/" + sneaker.getId();
	}
}
