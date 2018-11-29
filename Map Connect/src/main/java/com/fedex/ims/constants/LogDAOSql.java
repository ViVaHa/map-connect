package com.fedex.ims.constants;

public class LogDAOSql {
	public static final String INSERT_LOG = "INSERT INTO emailaudit(subject,content,isMailSent,createdOn)"
			+ " VALUES (?, ?, ?, now())";
	public static final String INSERT_CLIENT_LOG = "INSERT INTO usergeneratedlatlong(latitude,longitude,radius,createdDate)"
			+ " VALUES (?, ?, ?, now())";
	public static final String GET_EMAIL_AUDIT_ID = "SELECT MAX(id) FROM emailaudit";
	public static final String INSERT_EMAIL_LOG = "INSERT INTO useremailaudit(userId,emailAuditId)" + " VALUES (?,?)";
}
