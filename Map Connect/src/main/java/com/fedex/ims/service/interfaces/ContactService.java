package com.fedex.ims.service.interfaces;

import java.util.List;

import com.fedex.ims.model.Contact;

public interface ContactService {

	public int create(Contact contact);

	public int update(Contact contact);

	public void delete(int contactId);

	public Contact get(int contactId);

	public List<Contact> list();
}
