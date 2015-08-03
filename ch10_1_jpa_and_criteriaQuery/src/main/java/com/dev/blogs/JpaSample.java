package com.dev.blogs;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.dev.blogs.service.ContactService;
import com.dev.blogs.service.ContactServiceNativeQuery;

import dom.dev.blogs.model.Contact;
import dom.dev.blogs.model.ContactTelDetail;
import dom.dev.blogs.model.Hobby;

public class JpaSample {
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:app-context.xml");
		context.refresh();
		
		/*ContactService contactService = context.getBean("jpaContactService", ContactService.class);
		
		List<Contact> contacts = contactService.findAllWithDetail();
		listContacts(contacts);*/
		
		/*ContactSummaryUntypeImpl contactSumaryUntype = context.getBean("contactSummaryUntype", ContactSummaryUntypeImpl.class);
		contactSumaryUntype.displayAllContactSummary();*/
		
		/*ContactSummaryService contactSummaryService = context.getBean("contactSummaryService", ContactSummaryService.class);
		List<ContactSummary> contacts = contactSummaryService.findAll();
		
		for (ContactSummary contactSummary : contacts) {
			System.out.println(contactSummary);
		}*/
		
		/*ContactService contactService = context.getBean("jpaContactService", ContactService.class);
		
		Contact contact = new Contact();
		contact.setFirstName("Michael");
		contact.setLastName("Jackson");
		contact.setBirthDate(new Date());
		ContactTelDetail contactTelDetail = new ContactTelDetail("Home", "11111111111111");
		contact.addContactTelDetail(contactTelDetail);
		contactTelDetail = new ContactTelDetail("Mobile", "2222222222222222");
		contact.addContactTelDetail(contactTelDetail);
		contactService.save(contact);
		List<Contact> contacts = contactService.findAllWithDetail();
		listContacts(contacts);
		
		contact = contactService.findById(1l);
		System.out.println("");
		System.out.println("Contact with id 1: " + contact);
		System.out.println("");
		
		contact.setFirstName("Kim Fung");
		Set<ContactTelDetail> contactTels = contact.getContactTelDetails();
		ContactTelDetail toDeleteContactTel = null;
		for (ContactTelDetail contactTel: contactTels) {
			if (contactTel.getTelType().equals("Home")) {
				toDeleteContactTel = contactTel;
			}
		}
		contactTels.remove(toDeleteContactTel);
		contactService.save(contact);
		contacts = contactService.findAllWithDetail();
		
		listContacts(contacts);
		
		contact = contactService.findById(1l);
		contactService.delete(contact);
		contacts = contactService.findAllWithDetail();
		listContacts(contacts);*/
		
		ContactServiceNativeQuery contactServiceNativeQuery = context.getBean("contactServiceNativeQueryImpl", ContactServiceNativeQuery.class);
		List<Contact> contacts = contactServiceNativeQuery.findByCriteriaQuery("John", "Smith");
		listContacts(contacts);
	}
	
	private static void listContacts(List<Contact> contacts) {
		System.out.println("");
		System.out.println("Listing contacts without details:");
		for (Contact contact : contacts) {
			System.out.println(contact);
			if (contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
					System.out.println("---" + contactTelDetail);
				}				
			}
			/*if (contact.getHobbies() != null) {
				for (Hobby hobby : contact.getHobbies()) {
					System.out.println("---" + hobby);
				}
			}*/
			System.out.println();
		}
	}
}