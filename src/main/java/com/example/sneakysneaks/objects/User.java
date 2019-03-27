package com.example.sneakysneaks.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class User {
	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private String description;
	private String email;
	private String phoneNumber;

	public User() {
		
	}
	public User(String firstName, String lastName, String description, String email, String phoneNumber) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDescription(description);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
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
	
	
//	@Override
//    public String toString() {
//        return String.format(
//                "Customer[id=%d, firstName='%s', lastName='%s']",
//                id, firstName, lastName);
//    }

	
}