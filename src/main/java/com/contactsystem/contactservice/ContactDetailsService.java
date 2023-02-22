package com.contactsystem.contactservice;

import java.util.List;

import com.contactsystem.contactrequests.ContactDetailsRequest;
import com.contactsystem.entity.Contacts;

public interface ContactDetailsService {
	public Contacts addContact(ContactDetailsRequest contactRequest);
	public Contacts getContactDataBasedOnId(Integer contactId);
	public List<Contacts> getAllContactsDetails();
	public Contacts deleteContact(Integer deleteId);
	public Contacts updateContactsData(ContactDetailsRequest updatecontactDetailsRequest);
	//searching
	
	List<Contacts> searchContactfirstname(String fname);
	List<Contacts> searchContactlastname(String lname);
	List<Contacts> searchContactemail(String email);
	}
