package com.dev.blogs;

import java.util.Date;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.dev.blogs.model.ContactAudit;
import com.dev.blogs.service.ContactAuditService;

public class SpringJpaAuditSample {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring-data-app-context.xml");
		ctx.refresh();
		
		ContactAuditService contactService = ctx.getBean("contactAuditService", ContactAuditService.class);
		
		List<ContactAudit> contacts = contactService.findAll();
		listContacts(contacts);
		
		// Add new contact
		System.out.println("Add new contact");
		ContactAudit contact = new ContactAudit();
		contact.setFirstName("Michael");
		contact.setLastName("Jackson");
		contact.setBirthDate(new Date());
		contactService.save(contact);
		contacts = contactService.findAll();
		listContacts(contacts);	
		
		// Find by id
		contact = contactService.findById(1l);
		System.out.println("");
		System.out.println("Contact with id 1:" + contact);
		System.out.println("");
		
		// Update contact
		System.out.println("Update contact");
		contact.setFirstName("Tom");
		contactService.save(contact);
		contacts = contactService.findAll();
		listContacts(contacts);
		
		// Find audit record by revision
		ContactAudit oldContact = contactService.findAuditByRevision(1l, 1);
		System.out.println("");
		System.out.println("Old Contact with id 1 and rev 1:" + oldContact);	
		System.out.println("");
		oldContact = contactService.findAuditByRevision(1l, 2);
		System.out.println("");
		System.out.println("Old Contact with id 1 and rev 2:" + oldContact);	
		System.out.println("");
	}
	
	private static void listContacts(List<ContactAudit> contacts) {
        System.out.println("");
        System.out.println("Listing contacts without details:");
        for (ContactAudit contact : contacts) {
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