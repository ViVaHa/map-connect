package com.fedex.ims.model;

public class Message {
	private String subject;

	public Message() {
		// TODO Auto-generated constructor stub
	}

	Message(String content, String subject) {
		this.content = content;
		this.subject = subject;
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

	private String content;

}
