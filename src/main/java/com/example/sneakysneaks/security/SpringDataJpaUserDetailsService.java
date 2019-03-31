package com.example.sneakysneaks.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.sneakysneaks.database.SneakyUserRepository;
import com.example.sneakysneaks.objects.SneakyUser;

@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {
	private final SneakyUserRepository repository;

	@Autowired
	public SpringDataJpaUserDetailsService(SneakyUserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		SneakyUser user = this.repository.findByEmail(email);
		return new User(user.getEmail(), user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getRoles()));
	}

}
