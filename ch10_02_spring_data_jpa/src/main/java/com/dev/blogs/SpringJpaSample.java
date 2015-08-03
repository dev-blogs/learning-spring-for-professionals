package com.dev.blogs;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;
import com.dev.blogs.model.Contact;
import com.dev.blogs.model.ContactTelDetail;
import com.dev.blogs.model.Hobby;
import com.dev.blogs.service.ContactService;

public class SpringJpaSample {
	public static void main(String [] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:spring-data-app-context.xml");
		context.refresh();
		
		ContactService contactService = context.getBean("springJpaContactService", ContactService.class);
		List<Contact> contacts = contactService.findAll();
		listContacts(contacts);
		
		contacts = contactService.findByFirstName("Clarence");
		listContacts(contacts);
		
		contacts = contactService.findByFirstNameAndLastname("Clarence", "Ho");
		listContacts(contacts);
	}
	
	private static void listContacts(List<Contact> contacts) {
		System.out.println("");
		System.out.println("Listing contacts without details:");
		for (Contact contact : contacts) {
			System.out.println(contact);
			/*if (contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
					System.out.println("---" + contactTelDetail);
				}				
			}
			if (contact.getHobbies() != null) {
				for (Hobby hobby : contact.getHobbies()) {
					System.out.println("---" + hobby);
				}
			}*/
			System.out.println();
		}
	}
}