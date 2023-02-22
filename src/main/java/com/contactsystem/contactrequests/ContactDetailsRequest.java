package com.contactsystem.contactrequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ContactDetailsRequest {
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
}

