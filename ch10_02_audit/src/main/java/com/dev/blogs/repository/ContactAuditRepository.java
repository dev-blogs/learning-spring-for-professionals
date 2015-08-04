package com.dev.blogs.repository;

import org.springframework.data.repository.CrudRepository;
import com.dev.blogs.model.ContactAudit;

public interface ContactAuditRepository extends CrudRepository<ContactAudit, Long> { 
}