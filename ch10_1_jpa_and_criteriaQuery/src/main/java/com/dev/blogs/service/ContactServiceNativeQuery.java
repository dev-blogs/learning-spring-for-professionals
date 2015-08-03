package com.dev.blogs.service;

import java.util.List;
import dom.dev.blogs.model.Contact;

public interface ContactServiceNativeQuery {
	public List<Contact> findAllByNativeQuery();
	public List<Contact> findByCriteriaQuery(String firstName, String lastName);
}