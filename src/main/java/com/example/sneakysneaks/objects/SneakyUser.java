package com.example.sneakysneaks.objects;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;



@Entity
@ToString(exclude = "password")
@Data
public class SneakyUser {
	private String name;
	private @Id @GeneratedValue Long id;
	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	private @JsonIgnore String password;
	private String description;
	private @JsonIgnore String email;
	private @JsonIgnore String phoneNumber;

	public SneakyUser() {

	}

	private @JsonIgnore String[] roles;

	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}

	public SneakyUser(String name, String description, String email, String phoneNumber, String password, String... roles) {
		this.setName(name);
		this.setDescription(description);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
		this.setPassword(password);
		this.roles=roles;
	}
	
	/**
	 * @return the password hashed
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
	@Override
	public int hashCode() {

		int result = Objects.hash(name, email, password);
		result = 31 * result + Arrays.hashCode(roles);
		return result;
	}

	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SneakyUser user = (SneakyUser) o;
		return Objects.equals(id, user.id) &&
				Objects.equals(name, user.name) &&
			Objects.equals(email, user.email) &&
			Objects.equals(password, user.password) &&
			Arrays.equals(roles, user.roles);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
