package com.contactsystem.contactservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.contactsystem.contactrepository.ContactsDetailsRepository;
import com.contactsystem.entity.Contacts;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private ContactsDetailsRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Contacts user = repository.findByFirstName(username);
		
		return new MyUserDetails(user);
	}
}
