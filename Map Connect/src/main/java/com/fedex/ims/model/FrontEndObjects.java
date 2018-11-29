package com.fedex.ims.model;

import java.util.List;

public class FrontEndObjects {
	private List<Circle> circles;
	private Msg message;

	public FrontEndObjects() {
		// TODO Auto-generated constructor stub
	}

	public FrontEndObjects(List<Circle> circles, Msg message) {
		this.circles = circles;
		this.message = message;
	}

	public List<Circle> getCircles() {
		return circles;
	}

	public void setCircles(List<Circle> circles) {
		this.circles = circles;
	}

	public Msg getMessage() {
		return message;
	}

	public void setMessage(Msg message) {
		this.message = message;
	}

}
