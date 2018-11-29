package com.fedex.ims.constants;

public class ContactDAOSql {

	public static final String UPDATE_CONTACT = "UPDATE contact SET name=?, email=?, address=?, "
			+ "telephone=? WHERE contact_id=?";

	public static final String INSERT_CONTACT = "INSERT INTO contact (name, email, address, telephone)"
			+ " VALUES (?, ?, ?, ?)";

	public static final String DELETE_CONTACT = "DELETE FROM contact WHERE contact_id=?";

	public static final String SELECT_CONTACTS = "SELECT * FROM contact";

	public static final String SELECT_CONTACT = "SELECT * FROM contact WHERE contact_id=";

	public static final String   CONTACT_ID = "contact_id";

	public static final String NAME = "name";

	public static final String EMAIL = "email";

	public static final String ADDRESS = "address";

	public static final String TELEPHONE = "telephone";
	

	private ContactDAOSql() {

	}
}
