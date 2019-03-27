package com.example.sneakysneaks.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.sneakysneaks.objects.Sneaker;
import com.example.sneakysneaks.objects.User;
import com.example.sneakysneaks.database.UserRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {
	private final SneakerRepository sneakerRepo;
	private final UserRepository repository;

	@Autowired
	public DatabaseLoader(UserRepository repository, SneakerRepository sneakerRepo) {
		this.repository = repository;
		this.sneakerRepo = sneakerRepo;
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
		
		this.sneakerRepo.save(new Sneaker ("HealthCheck", "HealthCheck", 0, 0, "This is a test","https://www.google.com/", null));
		
		this.sneakerRepo.save(new Sneaker ("HealthCheck", "HealthCheck", 0, 0, "This is a test","https://www.google.com/", null));
		this.sneakerRepo.save(new Sneaker ("Nike", "Off-White Air Force One", 13, 150, "This AF1 comes with a black upper, white Nike “Swoosh”, black midsole, and a black sole.","bladsf", null));
		this.sneakerRepo.save(new Sneaker ("Nike", "Sean Wotherspoon Air Maxes", 13, 160, "These are the Sean WOtherspoon grails", "rfrenjlink", null));
		this.sneakerRepo.save(new Sneaker ("Nike", "Travis Scott Air Force One", 9, 130,"la flame travis scott joints", "link is here", null));
		this.sneakerRepo.save(new Sneaker ("Adidas", "Triple White UltraBoost", 13, 180, "the og freshman year sneaker", "sdgr", null));
		this.sneakerRepo.save(new Sneaker ("Adidas", "Bronze Raf Simons Stan Smiths", 7, 120, "oh you fancy huh", "rfdsv", null));
		this.sneakerRepo.save(new Sneaker ("Adidas", "Yeezy Supermoon Yellow", 7, 220, "yeezy yeezy yeezy", "rfresg", null));
		this.sneakerRepo.save(new Sneaker ("Reebok", "Vetements Instapump fury", 9, 150, "shouts to my son kareem for recommending these", "reg", null));
		this.sneakerRepo.save(new Sneaker ("Nike", "Off-White Air Force One", 9, 150, "this nigga sulaiman really baited on this", "rgefgv", null));
		this.sneakerRepo.save(new Sneaker ("Nike", "Off-White Air Force One", 11, 150, "so fire we had to put it twice", "fvdsv", null));
		this.sneakerRepo.save(new Sneaker ("Nike", "Off-White Air Force One", 11, 150, "glllltlttttt", "fdvffvd", null));
	}
}
