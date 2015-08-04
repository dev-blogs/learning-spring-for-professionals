package com.dev.blogs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.blogs.model.ContactAudit;
import com.dev.blogs.repository.ContactAuditRepository;
import com.google.common.collect.Lists;

@Service("contactAuditService")
@Repository
@Transactional
public class ContactAuditServiceImpl implements ContactAuditRepository {
	@Autowired
	private ContactAuditRepository contactAuditRepository;

	public ContactAudit save(ContactAudit entity) {
		return contactAuditRepository.save(entity);
	}

	public Iterable<ContactAudit> save(Iterable<? extends ContactAudit> entities) {
		return null;
	}

	public ContactAudit findOne(Long id) {
		return null;
	}

	public boolean exists(Long id) {
		return false;
	}

	@Transactional(readOnly=true)
	public Iterable<ContactAudit> findAll() {
		return Lists.newArrayList(contactAuditRepository.findAll());
	}
	
	public ContactAudit findById(Long id) {
		return contactAuditRepository.findOne(id);
	}

	public long count() {
		return 0;
	}

	public void delete(Long id) {
	}

	public void delete(ContactAudit entity) {
	}

	public void delete(Iterable<? extends ContactAudit> entities) {
	}

	public void deleteAll() {
	}
}