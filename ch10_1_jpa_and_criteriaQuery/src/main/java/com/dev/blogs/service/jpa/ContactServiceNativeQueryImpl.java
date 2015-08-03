package com.dev.blogs.service.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.blogs.service.ContactServiceNativeQuery;

import dom.dev.blogs.model.Contact;
import dom.dev.blogs.model.Contact_;

@Service("contactServiceNativeQueryImpl")
@Repository
@Transactional
public class ContactServiceNativeQueryImpl implements ContactServiceNativeQuery {
	private Log log = LogFactory.getLog(ContactServiceNativeQueryImpl.class);
	final static String ALL_CONTACT_NATIVE_QUERY = "select id, first_name, last_name, birth_date, version from contact";

	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly=true)
	public List<Contact> findAllByNativeQuery() {
		return em.createNativeQuery(ALL_CONTACT_NATIVE_QUERY, "contactResult").getResultList();
	}

	@Transactional(readOnly=true)
	public List<Contact> findByCriteriaQuery(String firstName, String lastName) {
		log.info("Finding contact for firstname: " + firstName + " and lastname: " + lastName);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		// В параметр передается тип результата. Указывается ожидаемый тип результата запроса
		CriteriaQuery<Contact> criteriaQuery = cb.createQuery(Contact.class);

		// Возвращается объект корня запроса, соответствующий сущности Contact
		// Этот запрос без ничего просто выборка всех записей		
		Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
		
		// Корень запроса это что-то вроде выборки самых верхних записей иерархической структуры таблиц в базе данных, если
		// связи между таблицами в базе данных на минутку представим в виде иерархической структуры, а не в виде отношений.
		// То есть, например если объекте Contacts содержится список деталей контакта (отношение один ко многим в базе данных)
		// то корнем запроса будет просто список объектов Contact без списков деталей которые содержатся в каждом объекте Contact,
		// потому что они следуют дальше по иерархии. Как-то так.
		
		// а уже потом, с помощью этого корня запроса, мы можем вытащить все что относится к сущности Contact
		// например, все детали, которые ассоциируются с таблицей Contact.
		// Соотнести каждую деталь к своему контакту
		// К каждому контакту сопоставить свою деталь, не важно содержит ли контакт деталь или нет
		// то есть берется контакт и его первая деталь, потом опять этот контакт и вторая деталь
		// в случае если контакт не содержит деталей, то контакт все равно берется
		contactRoot.fetch(Contact_.contactTelDetails, JoinType.LEFT);
		//contactRoot.fetch(Contact_.hobbies, JoinType.LEFT);
		
		// Этой инструкцие мы просто предотвращаем дублирования, которые образовались в резульатате LEFT JOIN
		criteriaQuery.select(contactRoot).distinct(true);
		
		// Создать несколько ограничений по полям.
		// conjunction() применяется для объединения нескольких ограничений
		Predicate criteria = cb.conjunction(); // conjunction - соединение, связь, коньюнкция
		
		Predicate p = null;
		if (firstName != null) {
			p = cb.equal(contactRoot.get(Contact_.firstName), firstName);
			criteria = cb.and(criteria, p);
		}
		/*if (lastName != null) {
			Predicate p = cb.equal(contactRoot.get(Contact_.lastName), lastName);
			criteria = cb.and(criteria, p);
		}*/
		
		// Сформированный предикат передается в виде конструкции where
		criteriaQuery.where(p);
		
		List<Contact> result = em.createQuery(criteriaQuery).getResultList();
		return result;
	}
}