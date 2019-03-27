package com.example.sneakysneaks.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.sneakysneaks.objects.User;
import com.example.sneakysneaks.database.UserRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {
	private final UserRepository repository;

	@Autowired
	public DatabaseLoader(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... strings) throws Exception {
		this.repository.save(new User("Frodo", "Baggins", "ring bearer", "booger@gmail.com", "6783545768", "poop101", strings));
		this.repository.save(new User("chika", "Henry", "ring maker", "poop@gmail.com", "65343444348", "poop101", strings));
		this.repository.save(new User("Ezuku", "Paul", "ring wearer", "hooter@gmail.com", "67543453768", "poop101", strings));
		this.repository.save(new User("Okoro", "Fake", "ring thief", "foober@gmail.com", "67121268", "poop101", strings));
		this.repository.save(new User("Paul", "Sean", "ring collector", "noober@gmail.com", "99983545768", "poop101", strings));
		this.repository.save(new User("West", "Kardashian", "ring eater", "zoober@gmail.com", "678049858", "poop101", strings));
		this.repository.save(new User("Devlin", "Mack", "ring investor", "loober@gmail.com", "67834343768", "poop101", strings));
		this.repository.save(new User("Twain", "Mark", "ring designer", "doober@gmail.com", "64324768", "poop101", strings));
	
	}
}
