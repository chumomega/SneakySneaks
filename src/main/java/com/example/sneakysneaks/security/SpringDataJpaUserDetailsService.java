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
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		SneakyUser user = this.repository.findByfirstName(name);
		return new User(user.getFirstName(), user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getRoles()));
	}

}
