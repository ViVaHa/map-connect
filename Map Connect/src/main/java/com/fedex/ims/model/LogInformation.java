package com.fedex.ims.model;

import java.sql.Date;

public class LogInformation {
	private String subject;
	private String content;
	private String status;
	private Date date;

	public LogInformation(String subject, String content, String status, Date date) {
		this.subject = subject;
		this.content = content;
		this.status = status;
		this.date = date;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
