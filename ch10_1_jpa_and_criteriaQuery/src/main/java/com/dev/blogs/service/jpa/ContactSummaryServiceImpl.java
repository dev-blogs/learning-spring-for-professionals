package com.dev.blogs.service.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dev.blogs.service.ContactSummaryService;
import dom.dev.blogs.model.ContactSummary;

/**
 * Во многих случаях возникает необходимость отправить запрос базе данніх и манипулировать результатами по собственному усмотрению,
 * а не сохранять их в отображенном сущностном классе.
 * Этот способ применим если требуется вручную манипулировать результирующим набором, без сохранения результата выборки в сущностном классе.
 * @author zheka
 *
 */
@Service("contactSummaryService")
@Repository
@Transactional
public class ContactSummaryServiceImpl implements ContactSummaryService {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Запрос со специальным типом результата и конструирующим выражением
	 */
	@Transactional(readOnly=true)
	public List<ContactSummary> findAll() {
		List<ContactSummary> result = em.createQuery("select new dom.dev.blogs.model.ContactSummary("
				+ "c.firstName, c.lastName, t.telNumber) "
				+ "from Contact c left join c.contactTelDetails t "
				+ "where t.telType='Home'",
				ContactSummary.class).getResultList();
		return result;
	}

}