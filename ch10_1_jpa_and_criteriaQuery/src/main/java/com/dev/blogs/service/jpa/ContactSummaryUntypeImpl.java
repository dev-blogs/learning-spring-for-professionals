package com.dev.blogs.service.jpa;

import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Этот способ применим если требуется вручную манипулировать результирующим набором, без сохранения результата выборки в сущностном классе.
 * @author zheka
 *
 */
@Service("contactSummaryUntype")
@Repository
@Transactional
public class ContactSummaryUntypeImpl {
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Запрос с нетипизированными результатами
	 */
	@Transactional(readOnly=true)
	public void displayAllContactSummary() {
		List result = em.createQuery("select c.firstName, c.lastName, t.telNumber from Contact c left join c.contactTelDetails t where t.telType = 'Home'").getResultList();
		int count = 0;
		for (Iterator i = result.iterator(); i.hasNext();) {
			Object[] values = (Object[]) i.next();
			System.out.println(++count + ": " + values[0] + ", " + values[1] + ", " + values[2]);
		}
	}
}