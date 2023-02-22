package com.contactsystem.contactsresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ContactBodyResponse {
	private Boolean isError;
	private String message;
	private Object data;

}
