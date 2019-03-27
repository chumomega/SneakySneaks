package com.example.sneakysneaks.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;



@Entity
@ToString(exclude = "password")
@Data
public class User {
	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	private Long id;
	private String firstName;
	
	//public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	private @JsonIgnore String password;

	private String lastName;
	private String description;
	private String email;
	private String phoneNumber;

	public User() {
		
	}
	
	private String[] roles;

//	public void setPassword(String password) {
//		this.password = PASSWORD_ENCODER.encode(password);
//	}
	
	public User(String firstName, String lastName, String description, String email, String phoneNumber, String password, String... roles) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDescription(description);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
		//this.setPassword(password);
		this.roles=roles;
	}
	


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the roles
	 */
	public String[] getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
	
//	@Override
//    public String toString() {
//        return String.format(
//                "Customer[id=%d, firstName='%s', lastName='%s']",
//                id, firstName, lastName);
//    }

	
}