package com.fedex.ims.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedex.ims.dao.interfaces.ContactDAO;
import com.fedex.ims.model.Contact;
import com.fedex.ims.service.interfaces.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	private static final Logger LOG = LogManager.getLogger(ContactServiceImpl.class);

	@Autowired
	private ContactDAO contactDAO;

	@Override
	public int update(Contact contact) {
		LOG.info("Inside ContactServiceImpl update ++++");
		return contactDAO.saveOrUpdate(contact);
	}

	@Override
	public void delete(int contactId) {
		LOG.info("Inside ContactServiceImpl Delete ++++");
		contactDAO.delete(contactId);
	}

	@Override
	public int create(Contact contact) {
		LOG.info("Inside ContactServiceImpl Create ++++");
		return contactDAO.saveOrUpdate(contact);
	}

	@Override
	public Contact get(int contactId) {
		LOG.info("Inside ContactServiceImpl GET ++++");
		return contactDAO.get(contactId);
	}

	@Override
	public List<Contact> list() {
		LOG.info("Inside ContactServiceImpl List ++++");
		return contactDAO.list();
	}

}
