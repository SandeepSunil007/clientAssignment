package com.contactsystem.contactservice;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.contactsystem.entity.Contacts;

public class MyUserDetails implements UserDetails {

	@Autowired
	private Contacts contacts;

	public MyUserDetails(Contacts contacts) {
		super();
		this.contacts = contacts;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(contacts.getRoles());
		ArrayList<SimpleGrantedAuthority> arrayList = new ArrayList<SimpleGrantedAuthority>();
		arrayList.add(simpleGrantedAuthority);
		return arrayList;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return contacts.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return contacts.getFirstName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
