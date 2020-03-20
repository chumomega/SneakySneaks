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
	public DatabaseLoader(SneakerRepository sneakerRepo, SneakyUserRepository userRepo ) {
		this.userRepo = userRepo;
		this.sneakerRepo = sneakerRepo;
	}

	@Override
	public void run(String... strings) throws Exception {
		SneakyUser frodo = this.userRepo.save(new SneakyUser("Frodo", "ring bearer", "booger@gmail.com", "6783545768", "poop101", "SNEAKY_USER"));
		SneakyUser chika = this.userRepo.save(new SneakyUser("chika", "ring maker", "poop@gmail.com", "65343444348", "poop101", "SNEAKY_USER"));
		SneakyUser ezuku = this.userRepo.save(new SneakyUser("Ezuku", "ring wearer", "hooter@gmail.com", "67543453768", "poop101", "SNEAKY_USER"));
//
		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken("Frodo", "poop101", AuthorityUtils.createAuthorityList("SNEAKY_USER")));

		this.sneakerRepo.save(new Sneaker ("Nike", "Off-White Air Force One", 13, 150, "This AF1 comes with a black upper, white Nike “Swoosh”, black midsole, and a black sole.","https://image.goat.com/crop/750/attachments/product_template_additional_pictures/images/017/794/463/original/394710_01.jpg.jpeg?1547236891", frodo));
		this.sneakerRepo.save(new Sneaker ("Nike", "Sean Wotherspoon Air Maxes", 13, 160, "These are the Sean WOtherspoon grails", "https://image.goat.com/crop/750/attachments/product_template_additional_pictures/images/017/794/463/original/394710_01.jpg.jpeg?1547236891", frodo));


		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken("chika", "doesn't matter", AuthorityUtils.createAuthorityList("SNEAKY_USER")));

		this.sneakerRepo.save(new Sneaker (
				"Nike", "Off-White Air Force One", 13, 150, "This AF1 comes with a black upper, white Nike “Swoosh”, black midsole, and a black sole.","https://image.goat.com/crop/750/attachments/product_template_additional_pictures/images/017/794/463/original/394710_01.jpg.jpeg?1547236891", chika));
		this.sneakerRepo.save(new Sneaker (
				"Nike", "Sean Wotherspoon Air Maxes", 13, 160, "These are the Sean WOtherspoon grails", "https://image.goat.com/crop/750/attachments/product_template_additional_pictures/images/011/255/124/original/329483_01.jpg.jpeg?1523485320", chika));
		this.sneakerRepo.save(new Sneaker ("Nike", "Travis Scott Air Force One", 9, 130,"la flame travis scott joints", "https://image.goat.com/crop/750/attachments/product_template_additional_pictures/images/011/255/124/original/329483_01.jpg.jpeg?1523485320", chika));
		this.sneakerRepo.save(new Sneaker ("Adidas", "Triple White UltraBoost", 13, 180, "the og freshman year sneaker", "https://image.goat.com/crop/750/attachments/product_template_additional_pictures/images/011/255/124/original/329483_01.jpg.jpeg?1523485320", chika));
//
//
		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken("Ezuku", "doesn't matter", AuthorityUtils.createAuthorityList("SNEAKER_MANAGER")));

		this.sneakerRepo.save(new Sneaker ("Reebok", "Vetements Instapump fury", 9, 150, "shouts to my son kareem for recommending these", "https://image.goat.com/crop/750/attachments/product_template_additional_pictures/images/011/255/124/original/329483_01.jpg.jpeg?1523485320", ezuku));
		this.sneakerRepo.save(new Sneaker ("Nike", "Off-White Air Force One", 9, 150, "this nigga sulaiman really baited on this", "https://image.goat.com/crop/750/attachments/product_template_additional_pictures/images/011/255/124/original/329483_01.jpg.jpeg?1523485320", ezuku));
		this.sneakerRepo.save(new Sneaker ("Adidas", "Bronze Raf Simons Stan Smiths", 7, 120, "oh you fancy huh", "https://image.goat.com/crop/750/attachments/product_template_additional_pictures/images/011/255/124/original/329483_01.jpg.jpeg?1523485320", ezuku));

		SecurityContextHolder.clearContext();
	}
}
