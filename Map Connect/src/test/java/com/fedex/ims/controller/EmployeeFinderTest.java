package com.fedex.ims.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fedex.ims.config.WebAppConfigurationAware;
import com.fedex.ims.model.Contact;
import com.fedex.ims.request.dto.ContactForm;
import com.fedex.ims.service.interfaces.ContactService;
import com.fedex.ims.service.interfaces.EmployeeFinderService;
import com.fedex.ims.service.interfaces.LogService;
import com.fedex.ims.service.interfaces.MailerService;

public class EmployeeFinderTest extends WebAppConfigurationAware {

	@InjectMocks
	private EmployeeFinderController controller;

	@Mock
	private EmployeeFinderService employeeFinderService;
	@Mock
	private MailerService mailerService;
	@Mock
	private LogService logService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(restExceptionHandler).build();
	}

	@Test
	public void putContactForBadRequest() throws Exception {
		/*ContactForm contactForm = new ContactForm();
		contactForm.setName("acco");
		contactForm.setEmail("accoacco.com");
		contactForm.setAddress("raheja It Space");
		contactForm.setTelephone("1234567890");
		String contactFormJsonString = jsonMapper.writeValueAsString(contactForm);
		when(contactService.create(any(Contact.class))).thenReturn(1);
		mockMvc.perform(put("/contact").content(contactFormJsonString).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(content().string("{\"fieldErrors\":[{\"field\":\"email\",\"message\":\"{email.message}\"}]}"));
				*/
	} 
}
