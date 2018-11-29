package com.fedex.ims.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fedex.ims.dao.interfaces.ContactDAO;
import com.fedex.ims.model.Contact;
import com.fedex.ims.service.impl.ContactServiceImpl;
import com.fedex.ims.service.interfaces.ContactService;

public class ContactServiceTest extends BaseServiceTest {

	@Mock
	private ContactDAO contactDao;

	@InjectMocks
	private ContactService contactService = new ContactServiceImpl();

	private Contact contact;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Before
	public void setUpTestData() {
		contact = new Contact();
		contact.setName("acco");
		contact.setEmail("acco@acco.com");
		contact.setAddress("raheja It Space");
		contact.setTelephone("1234567890");
	}

	@Test
	public void update() {
		contact.setId(1);
		when(contactDao.saveOrUpdate(any(Contact.class))).thenReturn(1);
		Assert.assertEquals(1, contactService.update(contact));
	}

	@Test
	public void insert(){
		contact.setId(-1);
		when(contactDao.saveOrUpdate(any(Contact.class))).thenReturn(1);
		Assert.assertEquals(1, contactService.create(contact));
	}
	
	@Test
	public void delete() {
		Mockito.doNothing().when(contactDao).delete(1);
		contactService.delete(1);
	}

	@Test
	public void list() {
		contact.setId(1);
		List<Contact> contactList = new ArrayList<>();
		contactList.add(contact);
		when(contactDao.list()).thenReturn(contactList);
		Assert.assertEquals(1, contactService.list().size());
	}
	
	@Test
	public void get() {
		contact.setId(1);
		when(contactDao.get(1)).thenReturn(contact);
		Assert.assertEquals(1, contactService.get(1).getId());
	}
}
