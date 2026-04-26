package com.example.sneakysneaks.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.example.sneakysneaks.model.Sneaker;
import com.example.sneakysneaks.model.SneakyUser;

@Component
public class DatabaseLoader implements CommandLineRunner {
	private final SneakerRepository sneakerRepo;
	private final SneakyUserRepository userRepo;

	@Autowired
	public DatabaseLoader(SneakerRepository sneakerRepo, SneakyUserRepository userRepo) {
		this.userRepo = userRepo;
		this.sneakerRepo = sneakerRepo;
	}

	@Override
	public void run(String... strings) throws Exception {
		if (userRepo.count() > 0) {
			return;
		}

		SneakyUser frodo = userRepo.save(new SneakyUser("Frodo", "ring bearer", "booger@gmail.com", "6783545768", "poop101", "SNEAKY_USER"));
		SneakyUser chika = userRepo.save(new SneakyUser("chika", "ring maker", "poop@gmail.com", "65343444348", "poop101", "SNEAKY_USER"));
		SneakyUser ezuku = userRepo.save(new SneakyUser("Ezuku", "ring wearer", "hooter@gmail.com", "67543453768", "poop101", "SNEAKY_USER"));

		String stockx = "https://images.stockx.com/360/";
		String afOne = stockx + "Nike-Air-Force-1-Low-Off-White-Black-White/Images/Nike-Air-Force-1-Low-Off-White-Black-White/Lv2/img01.jpg?w=576&q=60&dpr=1&updated_at=1634923748&h=384";
		String airMax = stockx + "Nike-Air-Max-1-97-Sean-Wotherspoon-NA/Images/Nike-Air-Max-1-97-Sean-Wotherspoon-NA/Lv2/img01.jpg?w=576&q=60&dpr=1&updated_at=1698157683&h=384";
		String travis = stockx + "Nike-Air-Force-1-Low-Travis-Scott-Cactus-Jack/Images/Nike-Air-Force-1-Low-Travis-Scott-Cactus-Jack/Lv2/img01.jpg?w=576&q=60&dpr=1&updated_at=1635276243&h=384";
		String ultraBoost = stockx + "adidas-Ultra-Boost-10-DNA-Triple-White/Images/adidas-Ultra-Boost-10-DNA-Triple-White/Lv2/img01.jpg?w=576&q=60&dpr=1&updated_at=1678267280&h=384";
		String instapump = stockx + "Reebok-Instapump-Fury-Vetements-Doodle/Images/Reebok-Instapump-Fury-Vetements-Doodle/Lv2/img01.jpg?w=576&q=60&dpr=1&updated_at=1629402386&h=384";
		String stanSmith = "https://images.stockx.com/images/adidas-Stan-Smith-Raf-Simons-White.png?fit=fill&bg=FFFFFF&w=576&h=384&q=60&dpr=1&trim=color&updated_at=1626899757";

		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken("Frodo", "poop101", AuthorityUtils.createAuthorityList("SNEAKY_USER")));
		sneakerRepo.save(new Sneaker("Nike", "Off-White Air Force One", 13, 150, "Black upper, white Swoosh, black sole.", afOne, frodo));
		sneakerRepo.save(new Sneaker("Nike", "Sean Wotherspoon Air Max", 13, 160, "The Sean Wotherspoon grails.", airMax, frodo));

		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken("chika", "poop101", AuthorityUtils.createAuthorityList("SNEAKY_USER")));
		sneakerRepo.save(new Sneaker("Nike", "Travis Scott Air Force One", 9, 130, "La flame Travis Scott joints.", travis, chika));
		sneakerRepo.save(new Sneaker("Adidas", "Triple White UltraBoost", 13, 180, "The og freshman year sneaker.", ultraBoost, chika));

		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken("Ezuku", "poop101", AuthorityUtils.createAuthorityList("SNEAKY_USER")));
		sneakerRepo.save(new Sneaker("Reebok", "Vetements Instapump Fury", 9, 150, "Recommendation from a friend.", instapump, ezuku));
		sneakerRepo.save(new Sneaker("Adidas", "Bronze Raf Simons Stan Smiths", 7, 120, "Oh you fancy huh.", stanSmith, ezuku));

		SecurityContextHolder.clearContext();
	}
}
