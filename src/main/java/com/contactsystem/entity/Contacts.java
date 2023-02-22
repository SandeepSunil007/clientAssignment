package com.contactsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Contacts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "firstname is manditory")
	private String firstName;
	
	@NotBlank(message = "lastname is manditory")
	private String lastName;
	
	@Email(message = "email is manditory")
	private String email;
	
	@NotBlank(message = "phonenumber is manditory")
	private String phoneNumber;
	
	private String password;
}
