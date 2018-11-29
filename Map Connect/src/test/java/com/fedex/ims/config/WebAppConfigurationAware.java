package com.fedex.ims.config;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedex.ims.config.WebMvcConfig;
import com.fedex.ims.controller.RESTExceptionHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebAppConfiguration
@EnableTransactionManagement
@ContextConfiguration(classes = { WebMvcConfig.class, EmbeddedDataSourceConfig.class })
public abstract class WebAppConfigurationAware {

	@Inject
	protected WebApplicationContext wac;
	/** The json mapper. */
	protected final ObjectMapper jsonMapper = new ObjectMapper();

	protected MockMvc mockMvc;

	@Autowired
	protected RESTExceptionHandler restExceptionHandler;

	@Before
	public void before() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

}
