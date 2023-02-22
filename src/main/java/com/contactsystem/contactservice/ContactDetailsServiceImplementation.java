package com.contactsystem.contactservice;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contactsystem.contactrepository.ContactsDetailsRepository;
import com.contactsystem.contactrequests.ContactDetailsRequest;
import com.contactsystem.contactsexception.ContactIdNotFoundException;
import com.contactsystem.contactsresponse.ContactBodyResponse;
import com.contactsystem.entity.Contacts;

@Service
public class ContactDetailsServiceImplementation implements ContactDetailsService {

	@Autowired
	private ContactsDetailsRepository contactDetailsRepository;

	@Override
	public Contacts addContact(ContactDetailsRequest contactRequest) {
		Contacts contact = Contacts.builder().firstName(contactRequest.getFirstName())
				.lastName(contactRequest.getLastName()).email(contactRequest.getEmail())
				.phoneNumber(contactRequest.getPhoneNumber())
				.password(contactRequest.getPassword()).build();
		contactDetailsRepository.save(contact);
		return contact;
	}

	@Override
	public Contacts getContactDataBasedOnId(Integer contactId) {

		return contactDetailsRepository.findById(contactId)
				.orElseThrow(() -> new ContactIdNotFoundException(contactId + " Id Not exists in DataBase"));
	}

	@Override
	public List<Contacts> getAllContactsDetails() {
		return contactDetailsRepository.findAll().stream().map(x -> {
			ContactBodyResponse bodyResponse = ContactBodyResponse.builder().build();
			BeanUtils.copyProperties(bodyResponse, x);
			return x;
		}).collect(Collectors.toList());

	}

	@Override
	public Contacts deleteContact(Integer deleteId) {
		return contactDetailsRepository.findById(deleteId)
				.orElseThrow(() -> new ContactIdNotFoundException(deleteId + " Id Not exists in DataBase"));
	}

	@Override
	public Contacts updateContactsData(ContactDetailsRequest updatecontactDetailsRequest) {
		Contacts contact = contactDetailsRepository.findById(updatecontactDetailsRequest.getId())
				.orElseThrow(() -> new ContactIdNotFoundException("Batch is not found "));

		BeanUtils.copyProperties(updatecontactDetailsRequest, contact);
		Contacts save = contactDetailsRepository.save(contact);
		return save;
	}

	@Override
	public List<Contacts> searchContactfirstname(String name) {
		List<Contacts> contacts = contactDetailsRepository.searchContactfirstname(name);
		if (contacts.isEmpty()) {
			return Collections.emptyList();
		}
		return contacts;
	}

	@Override
	public List<Contacts> searchContactlastname(String lname) {

		List<Contacts> searchContactlastname = contactDetailsRepository.searchContactlastname(lname);
		if (searchContactlastname.isEmpty()) {
			return Collections.emptyList();
		}
		return searchContactlastname;
	}

	@Override
	public List<Contacts> searchContactemail(String email) {
		List<Contacts> searchContactemail = contactDetailsRepository.searchContactemail(email);
		if (searchContactemail.isEmpty()) {
			return Collections.emptyList();
		}
		return searchContactemail;
	}

}