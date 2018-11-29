package com.fedex.ims.dao.interfaces;

import java.util.List;

import com.fedex.ims.model.Contact;

public interface ContactDAO {

	public int saveOrUpdate(Contact contact);

	public void delete(int contactId);

	public Contact get(int contactId);

	public List<Contact> list();
}
