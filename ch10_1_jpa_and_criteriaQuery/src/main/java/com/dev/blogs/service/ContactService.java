package com.dev.blogs.service;

import java.util.List;

import dom.dev.blogs.model.Contact;

public interface ContactService {
	public List<Contact> findAll();
	public List<Contact> findAllWithDetail();
	public Contact findById(Long id);
	public Contact save(Contact contact);
	public void delete(Contact contact);
}