package com.dev.blogs.service;

import java.util.List;
import com.dev.blogs.model.ContactAudit;

public interface ContactAuditService {
	public List<ContactAudit> findAll();
	public ContactAudit findById(Long id);
	public ContactAudit save(ContactAudit contact);
	public ContactAudit findAuditByRevision(Long id, int revision);
}