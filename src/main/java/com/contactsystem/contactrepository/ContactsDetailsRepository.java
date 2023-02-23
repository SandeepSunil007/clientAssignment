package com.contactsystem.contactrepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.contactsystem.entity.Contacts;

public interface ContactsDetailsRepository extends JpaRepository<Contacts, Integer> {
	@Query("SELECT b FROM Contacts b  WHERE " + "b.firstName LIKE CONCAT('%', :fname, '%')")
	List<Contacts> searchContactfirstname(String fname);

	@Query("SELECT b FROM Contacts b  WHERE " + "b.lastName LIKE CONCAT('%', :lname, '%')")
	List<Contacts> searchContactlastname(String lname);

	@Query("SELECT b FROM Contacts b  WHERE " + "b.email LIKE CONCAT('%', :email, '%')")
	List<Contacts> searchContactemail(String email);
	
	public Contacts findByFirstName(String firstName);
	
}
