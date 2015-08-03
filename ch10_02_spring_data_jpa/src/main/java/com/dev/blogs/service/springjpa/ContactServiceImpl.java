package com.dev.blogs.service.springjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dev.blogs.model.Contact;
import com.dev.blogs.repository.ContactRepository;
import com.dev.blogs.service.ContactService;
import com.google.common.collect.Lists;

@Service("springJpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {
	// Вместо EntiyManager в класс службы мы должны внедрить интерфейс ContactRepository, и Spring Data JPA самостоятельно
	// сделает всю черновую работу.
	@Autowired
	private ContactRepository contactRepository;

	@Transactional(readOnly=true)
	public List<Contact> findAll() {
		return Lists.newArrayList(contactRepository.findAll());
	}

	public List<Contact> findByFirstName(String firstName) {
		return contactRepository.findByFirstName(firstName);
	}

	public List<Contact> findByFirstNameAndLastname(String firstName, String lastName) {
		return contactRepository.findByFirstNameAndLastName(firstName, lastName);
	}
}