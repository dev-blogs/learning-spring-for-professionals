package com.dev.blogs.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.dev.blogs.model.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {
	public List<Contact> findByFirstName(String firstName);
	public List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
}