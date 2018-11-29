package com.fedex.ims.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fedex.ims.config.WebAppConfigurationAware;
import com.fedex.ims.controller.ContactController;
import com.fedex.ims.model.Contact;
import com.fedex.ims.request.dto.ContactForm;
import com.fedex.ims.service.interfaces.ContactService;

public class ContactControllerTest extends WebAppConfigurationAware {

	@InjectMocks
	private ContactController controller;

	@Mock
	private ContactService contactService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(restExceptionHandler).build();
	}

	@Test
	public void putContactForBadRequest() throws Exception {
		ContactForm contactForm = new ContactForm();
		contactForm.setName("acco");
		contactForm.setEmail("accoacco.com");
		contactForm.setAddress("raheja It Space");
		contactForm.setTelephone("1234567890");
		String contactFormJsonString = jsonMapper.writeValueAsString(contactForm);
		when(contactService.create(any(Contact.class))).thenReturn(1);
		mockMvc.perform(put("/contact").content(contactFormJsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(content().string("{\"fieldErrors\":[{\"field\":\"email\",\"message\":\"{email.message}\"}]}"));
	}

	@Test
	public void putContactValidRequest() throws Exception {
		ContactForm contactForm = new ContactForm();
		contactForm.setName("acco");
		contactForm.setEmail("acco@acco.com");
		contactForm.setAddress("raheja It Space");
		contactForm.setTelephone("1234567890");
		String contactFormJsonString = jsonMapper.writeValueAsString(contactForm);
		when(contactService.create(any(Contact.class))).thenReturn(1);
		mockMvc.perform(put("/contact").content(contactFormJsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void getEmptyContactList() throws Exception {
		List<Contact> contactList = new ArrayList<>();
		String jsonResponse = jsonMapper.writeValueAsString(contactList);
		when(contactService.list()).thenReturn(contactList);
		mockMvc.perform(get("/contact")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().string(jsonResponse));
	}

	@Test
	public void getContactList() throws Exception {
		List<Contact> contactList = new ArrayList<>();
		contactList.add(new Contact("name", "email@hje.com", "address", "1234567890"));
		String jsonResponse = jsonMapper.writeValueAsString(contactList);
		when(contactService.list()).thenReturn(contactList);
		mockMvc.perform(get("/contact")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().string(jsonResponse));
	}

	@Test
	public void getContact() throws Exception {
		Contact contact = new Contact();
		contact.setId(25);
		contact.setEmail("acco@acco.com");
		String jsonResponse = jsonMapper.writeValueAsString(contact);
		when(contactService.get(any(Integer.class))).thenReturn(contact);
		mockMvc.perform(get("/contact/25")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().string(jsonResponse));
	}

}