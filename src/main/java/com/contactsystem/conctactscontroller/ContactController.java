package com.contactsystem.conctactscontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contactsystem.contactrequests.ContactDetailsRequest;
import com.contactsystem.contactservice.ContactDetailsService;
import com.contactsystem.contactservice.CustomUserDetailsService;
import com.contactsystem.contactsresponse.ContactBodyResponse;
import com.contactsystem.entity.AuthRequest;
import com.contactsystem.entity.AuthResponse;
import com.contactsystem.entity.Contacts;
import com.contactsystem.utility.JwtUtil;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

	@Autowired
	private ContactDetailsService contactService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@PostMapping("/addContact")
	public ResponseEntity<ContactBodyResponse> addContactData(@RequestBody ContactDetailsRequest contactRequest) {
		Contacts addContact = contactService.addContact(contactRequest);
		return ResponseEntity.ok(ContactBodyResponse.builder().isError(Boolean.FALSE)
				.message("Data added into DataBase").data(addContact).build());
	}

	@GetMapping("/getContactById/{id}")
	public ResponseEntity<ContactBodyResponse> getContactData(@PathVariable("id") Integer id) {
		Contacts contactDataBasedOnId = contactService.getContactDataBasedOnId(id);
		return ResponseEntity.ok(ContactBodyResponse.builder().isError(Boolean.FALSE)
				.message("Got the Data from data Base").data(contactDataBasedOnId).build());

	}

	@GetMapping("/getAllContacts")
	public ResponseEntity<ContactBodyResponse> getAllDataFromDB() {
		List<Contacts> allContactsDetails = contactService.getAllContactsDetails();
		return ResponseEntity.ok(ContactBodyResponse.builder().isError(Boolean.FALSE)
				.message("Got the full Contact Details..").data(allContactsDetails).build());

	}

	@DeleteMapping("/dataDeletedById/{deletedId}")
	public ResponseEntity<ContactBodyResponse> deleteByContactId(@PathVariable("deletedId") Integer deletedId) {
		Contacts deleteContact = contactService.deleteContact(deletedId);
		return ResponseEntity.ok(ContactBodyResponse.builder().isError(Boolean.FALSE)
				.message("Deleted Data Succesfully..").data(deleteContact).build());

	}

	@PutMapping("/contactDataUpdatById")
	public ResponseEntity<ContactBodyResponse> updateContactDetailsById(
			@RequestBody ContactDetailsRequest contactDetailsRequest) {
		Contacts updateContactsData = contactService.updateContactsData(contactDetailsRequest);
		return ResponseEntity.ok(ContactBodyResponse.builder().isError(Boolean.FALSE)
				.message("Updated Data Succesfully..").data(updateContactsData).build());
	}

	// Seraching

	@GetMapping("searchByFirstName/{fname}")
	public ResponseEntity<ContactBodyResponse> getDataByfirstname(@PathVariable("fname") String fname) {
		List<Contacts> searchBatchname = contactService.searchContactfirstname(fname);
		return ResponseEntity.ok(ContactBodyResponse.builder().isError(Boolean.FALSE)
				.message("Based on FirstName Data Displaying..").data(searchBatchname).build());

	}

	@GetMapping("searchByLastName/{lname}")
	public ResponseEntity<ContactBodyResponse> getDataBylastname(@PathVariable("lname") String lname) {
		List<Contacts> searchBatchname = contactService.searchContactlastname(lname);
		return ResponseEntity.ok(ContactBodyResponse.builder().isError(Boolean.FALSE)
				.message("Based on LastName Data Displaying..").data(searchBatchname).build());

	}

	@GetMapping("searchByEmail/{email}")
	public ResponseEntity<ContactBodyResponse> getDataByemail(@PathVariable("email") String email) {
		List<Contacts> searchBatchname = contactService.searchContactemail(email);
		return ResponseEntity.ok(ContactBodyResponse.builder().isError(Boolean.FALSE)
				.message("Based on LastName Data Displaying..").data(searchBatchname).build());

	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("inavalid username/password");

		}
		UserDetails loadUserByUsername = customUserDetailsService.loadUserByUsername(authRequest.getUsername());
		 String generateToken = jwtUtil.generateToken(loadUserByUsername);
		 return new ResponseEntity<AuthResponse>(new AuthResponse(generateToken),HttpStatus.OK);
	}
}