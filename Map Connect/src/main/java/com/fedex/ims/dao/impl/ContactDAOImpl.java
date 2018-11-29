package com.fedex.ims.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fedex.ims.constants.ContactDAOSql;
import com.fedex.ims.dao.interfaces.ContactDAO;
import com.fedex.ims.model.Contact;

@Repository
@Transactional(transactionManager = "transactionManager")
public class ContactDAOImpl implements ContactDAO {

	private static final Logger LOG = LogManager.getLogger(ContactDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int saveOrUpdate(Contact contact) {
		LOG.info("SaveUpdate method in ContactDAOImpl +++++");
		if (contact.getId() > 0) {
			// update
			String sql = ContactDAOSql.UPDATE_CONTACT;
			return jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getAddress(),
					contact.getTelephone(), contact.getId());
		} else {
			// insert
			String sql = ContactDAOSql.INSERT_CONTACT;
			return jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getAddress(),
					contact.getTelephone());
		}

	}

	@Override
	public void delete(int contactId) {
		String sql = ContactDAOSql.DELETE_CONTACT;
		jdbcTemplate.update(sql, contactId);
	}

	@Override
	public List<Contact> list() {
		String sql = ContactDAOSql.SELECT_CONTACTS;
		// List<Contact> listContact = jdbcTemplate.query(sql, (ResultSet rs,
		// int rowNum) -> {
		List<Contact> listContact = jdbcTemplate.query(sql, (rs, rowNum) -> {

			Contact aContact = new Contact();

			aContact.setId(rs.getInt(ContactDAOSql.CONTACT_ID));
			aContact.setName(rs.getString(ContactDAOSql.NAME));
			aContact.setEmail(rs.getString(ContactDAOSql.EMAIL));
			aContact.setAddress(rs.getString(ContactDAOSql.ADDRESS));
			aContact.setTelephone(rs.getString(ContactDAOSql.TELEPHONE));

			return aContact;

		});

		return listContact;
	}

	public List<Contact> listNoLambda() {
		String sql = ContactDAOSql.SELECT_CONTACTS;
		List<Contact> listContact = jdbcTemplate.query(sql, new RowMapper<Contact>() {

			@Override
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				Contact aContact = new Contact();

				aContact.setId(rs.getInt(ContactDAOSql.CONTACT_ID));
				aContact.setName(rs.getString(ContactDAOSql.NAME));
				aContact.setEmail(rs.getString(ContactDAOSql.EMAIL));
				aContact.setAddress(rs.getString(ContactDAOSql.ADDRESS));
				aContact.setTelephone(rs.getString(ContactDAOSql.TELEPHONE));

				return aContact;
			}

		});

		return listContact;
	}

	@Override
	public Contact get(int contactId) {
		String sql = ContactDAOSql.SELECT_CONTACT + contactId;
		// return jdbcTemplate.query(sql, (ResultSet rs) -> {
		return jdbcTemplate.query(sql, rs -> {
			if (rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getInt(ContactDAOSql.CONTACT_ID));
				contact.setName(rs.getString(ContactDAOSql.NAME));
				contact.setEmail(rs.getString(ContactDAOSql.EMAIL));
				contact.setAddress(rs.getString(ContactDAOSql.ADDRESS));
				contact.setTelephone(rs.getString(ContactDAOSql.TELEPHONE));
				return contact;
			}

			return null;
		});
	}

	public Contact getUsingNoLambda(int contactId) {
		String sql = ContactDAOSql.SELECT_CONTACT + contactId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Contact>() {

			@Override
			public Contact extractData(ResultSet rs) throws SQLException {
				if (rs.next()) {
					Contact contact = new Contact();
					contact.setId(rs.getInt(ContactDAOSql.CONTACT_ID));
					contact.setName(rs.getString(ContactDAOSql.NAME));
					contact.setEmail(rs.getString(ContactDAOSql.EMAIL));
					contact.setAddress(rs.getString(ContactDAOSql.ADDRESS));
					contact.setTelephone(rs.getString(ContactDAOSql.TELEPHONE));
					return contact;
				}

				return null;
			}

		});
	}

}
