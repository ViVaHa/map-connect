package com.fedex.ims.model;

public class ResponseObject {
	private String id;
	private Integer numberOfPeople;

	public ResponseObject() {
		// TODO Auto-generated constructor stub
	}

	public ResponseObject(String id, Integer numberOfPeople) {
		this.id = id;
		this.numberOfPeople = numberOfPeople;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(Integer numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

}
