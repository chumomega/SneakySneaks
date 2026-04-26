package com.example.sneakysneaks.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.sneakysneaks.model.SneakyUser;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

	private final SpringDataJpaUserDetailsService userDetailsService;

	public SecurityConfiguration(SpringDataJpaUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return SneakyUser.PASSWORD_ENCODER;
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/built/**", "/main.css", "/index.css", "/manifest.json").permitAll()
				.requestMatchers("/", "/login", "/signup", "/landing").permitAll()
				.requestMatchers("/api/save/**", "/api/me").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/sneakers", "/api/sneakers/**", "/api/profile", "/api/profile/**").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/landing", true)
				.failureUrl("/login?error")
				.permitAll()
			)
			.httpBasic(basic -> {})
			.csrf(csrf -> csrf.disable())
			.logout(logout -> logout
				.logoutSuccessUrl("/")
				.permitAll()
			);

		return http.build();
	}
}
