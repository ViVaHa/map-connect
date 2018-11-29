package com.fedex.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedex.ims.model.Contact;
import com.fedex.ims.request.dto.ContactForm;
import com.fedex.ims.service.interfaces.ContactService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactController.
 */
@RestController
@Api(value = "Contact Controller", description = "Contact Form API")
public class ContactController {

	/** The Constant LOG. */
	private static final Logger LOG = LogManager.getLogger(ContactController.class);

	/** The contact service. */
	@Autowired
	private ContactService contactService;

	/**
	 * New contact.
	 *
	 * @param contactForm the contact form
	 * @return the int
	 * @throws JsonProcessingException the json processing exception
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	
	@ApiOperation(httpMethod="PUT",value = "Create Contact")
	public int newContact(@Validated @RequestBody ContactForm contactForm) throws JsonProcessingException {
		LOG.info("Iam in PUT method");
		ObjectMapper obj = new ObjectMapper();
		LOG.info("Iam in PUT method =" + obj.writeValueAsString(contactForm));
		Contact contact = new Contact();
		BeanUtils.copyProperties(contactForm, contact);
		return contactService.create(contact);
		}

	/**
	 * Save contact.
	 *
	 * @param contactForm the contact form
	 * @return the int
	 */
	@RequestMapping(value = "/ "
			+ "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(httpMethod="POST",value = "Save Contact")
	public int saveContact(@RequestBody ContactForm contactForm) {
		LOG.info("Iam in Post method");
		Contact contact = new Contact();
		BeanUtils.copyProperties(contact, contactForm);
		return contactService.update(contact);
	}

	/**
	 * Delete contact.
	 *
	 * @param contactId the contact id
	 */
	@RequestMapping(value = "/contact/{contactId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(httpMethod="DELETE",value = "Delete Contact")
	public void deleteContact(@PathVariable int contactId) {
		contactService.delete(contactId);
	}

	/**
	 * Gets the contact.
	 *
	 * @param contactId the contact id
	 * @return the contact
	 */
	@RequestMapping(value = "/contact/{contactId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(httpMethod="GET",value = "Get Contact from ID")
	public Contact getContact(@PathVariable int contactId) {
		LOG.info("I am in contact get method ++++ ");
		return contactService.get(contactId);
	}
	
	/**
	 * Gets the all contacts.
	 *
	 * @return the all contacts
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(httpMethod="GET",value = "Get All Contacts")
	public List<Contact> getAllContacts() {
		LOG.info("I am in get all contacts method ++++ ");
		return contactService.list();
	}

}
