package com.example.sneakysneaks.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.example.sneakysneaks.model.SneakyUser;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//TODO - check out this security policy
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	private SpringDataJpaUserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(this.userDetailsService)
				.passwordEncoder(SneakyUser.PASSWORD_ENCODER);
	}
	

//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.cors().and()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/save/**").permitAll()
//                .and()
//                .httpBasic()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        
//    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/built/**", "/main.css").permitAll()
				.antMatchers("/api/save/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.defaultSuccessUrl("/", true)
				.permitAll()
				.and()
			.httpBasic()
				.and()
				/*TODO
				BASIC authentication is handy when you are experimenting with curl. Using curl to access a form-based
				system is daunting. It’s important to recognize that authenticting with any mechanism over HTTP (not HTTPS)
				puts you at risk of credentials being sniffed over the wire. CSRF is a good protocol to leave intact.
				 It is simply disabled to make interaction with BASIC and curl easier. In production, it’s best to leave it on.
				 */
			.csrf().disable()
			.logout()
				.logoutSuccessUrl("/");
	}


}
