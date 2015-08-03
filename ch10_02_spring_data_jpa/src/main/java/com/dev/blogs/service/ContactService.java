package com.dev.blogs.service;

import java.util.List;
import com.dev.blogs.model.Contact;

public interface ContactService {
	public List<Contact> findAll();
	public List<Contact> findByFirstName(String firstName);
	public List<Contact> findByFirstNameAndLastname(String firstName, String lastName);
}