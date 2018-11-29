package com.fedex.ims.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.fedex.ims.config.WebAppConfigurationAware;
import com.fedex.ims.dao.interfaces.ContactDAO;
import com.fedex.ims.model.Contact;

@Transactional(transactionManager="transactionManager")
@Commit
public class ContactDaoTest extends WebAppConfigurationAware {

	@Autowired
	private ContactDAO contactDao;

	private Contact contact;

	@Before
	public void setUpTestData() {
		contact = new Contact();
		contact.setName("acco");
		contact.setEmail("acco@acco.com");
		contact.setAddress("raheja It Space");
		contact.setTelephone("1234567890");
	}

	@Test
	public void saveOrUpdate() {
		Assert.assertEquals(1, contactDao.saveOrUpdate(contact));
	}
	
	@Test
	public void delete() {
		contactDao.delete(1);
		Assert.assertEquals(1,contactDao.list().size());
	}

	@Test
	public void list() {
		
		Assert.assertEquals(1, contactDao.list().size());
	}

	@Test
	public void get() {
		Assert.assertEquals(2, contactDao.get(2).getId());
	}

}
