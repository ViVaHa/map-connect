package com.fedex.ims.model;

//Circle POJO class
public class Circle {
	private double lat;
	private double lng;
	private double radius;
	private String id;

	public Circle() {
		// TODO Auto-generated constructor stub
	}

	public Circle(double lat, double lng, double radius, String id) {
		// TODO Auto-generated constructor stub
		this.lat = lat;
		this.lng = lng;
		this.radius = radius;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

}
