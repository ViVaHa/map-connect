package com.fedex.ims.request.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fedex.ims.constants.ValidationMessages;

public class ContactForm {

	@NotBlank(message = ValidationMessages.NOT_BLANK_MESSAGE)
	@Email(message = ValidationMessages.EMAIL_MESSAGE)
	private String email;

	@NotBlank(message = ValidationMessages.NOT_BLANK_MESSAGE)
	@Size(min = 1, max = 5, message = ValidationMessages.CONTACT_FORM_NAME_SIZE)
	private String name;

	@NotBlank(message = ValidationMessages.NOT_BLANK_MESSAGE)
	private String address;

	@NotBlank(message = ValidationMessages.NOT_BLANK_MESSAGE)
	private String telephone;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
