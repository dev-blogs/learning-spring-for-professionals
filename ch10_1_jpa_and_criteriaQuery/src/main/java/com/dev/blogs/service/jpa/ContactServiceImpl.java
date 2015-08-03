package com.dev.blogs.service.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dev.blogs.service.ContactService;
import dom.dev.blogs.model.Contact;

@Service("jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {
	private Log log = LogFactory.getLog(ContactServiceImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly=true)
	public List<Contact> findAll() {
		List<Contact> contacts = em.createNamedQuery("Contact.findAll", Contact.class).getResultList();
		return contacts;
	}
	
	@Transactional(readOnly=true)
	public List<Contact> findAllWithDetail() {
		List<Contact> contacts = em.createNamedQuery("Contact.findAllWithDetail", Contact.class).getResultList();
		return contacts;
	}

	@Transactional(readOnly=true)
	public Contact findById(Long id) {
		TypedQuery<Contact> query = em.createNamedQuery("Contact.findById", Contact.class);
		query.setParameter("id", id);		
		return query.getSingleResult();
	}

	/**
	 * Если из управляемого экземпляра удалить ContactTelDetail, то при сохранении контакта в базе данных
	 * запись, ассоциированная с ContactTelDetail будет удалена, так как атрибут orphanRemoval=true указывает поставщику
	 * JPA на необходимость удаления всех висячих записей, существующих в базе данных, но не принадлежащих какому-либо
	 * сохраненному объекту
	 */
	public Contact save(Contact contact) {
		if (contact.getId() == null) {
			log.info("Inserting new contact");
			// Диспетчер сущностей сохранит сущность и сделает ее управлемым экземпляром в рамках контекста постоянства.
			em.persist(contact);
		} else {
			em.merge(contact);
			log.info("Updating existing contact");
		}
		return contact;
	}
	
	/**
	 * Метод em.remove() удалит запись контакта, а так же всю связанную информацию, включая телефоны и хобби, поскольку в отображении
	 * было определено cascade=CascadeType.ALL.
	 */
	public void delete(Contact contact) {
		Contact mergedContact = em.merge(contact);
		em.remove(mergedContact);
		log.info("Contact with id: " + contact.getId() + " deleted successfully");
	}
}