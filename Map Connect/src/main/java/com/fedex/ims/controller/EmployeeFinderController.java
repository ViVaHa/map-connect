package com.fedex.ims.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fedex.ims.model.Circle;
import com.fedex.ims.model.SelectedPeople;
import com.fedex.ims.model.FrontEndObjects;
import com.fedex.ims.model.ResponseObject;
import com.fedex.ims.service.interfaces.EmployeeFinderService;
import com.fedex.ims.service.interfaces.LogService;
import com.fedex.ims.service.interfaces.MailerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// TODO: Auto-generated Javadoc
/**
 * The Class EmployeeFinderController.
 */
@RestController
@Api(value = "EmployeeFinderController", description = "Employee Finder API")
public class EmployeeFinderController {
	// private static final Logger LOG =
	/** The employee finder service. */
	// LogManager.getLogger(EmployeeFinderController.class);
	@Autowired
	private EmployeeFinderService employeeFinderService;

	/** The mailer service. */
	@Autowired
	private MailerService mailerService;

	/** The log service. */
	@Autowired
	private LogService logService;

	/**
	 * Save circles.
	 *
	 * @param frontEndObjects
	 *            the front end objects
	 * @return the list
	 * @throws JsonProcessingException
	 *             the json processing exception
	 * @throws ParseException
	 *             the parse exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	@RequestMapping(value = "/circles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(httpMethod = "POST", value = "Save Circles")
	// This is where the application receives the json values!
	public List<ResponseObject> saveCircles(@RequestBody FrontEndObjects frontEndObjects)
			throws JsonProcessingException, ParseException, SQLException {
		// obtain list of circles from JSON
		List<Circle> circles = frontEndObjects.getCircles();
		// Map each circle to users in that circle
		Map<String, List<SelectedPeople>> mymap = employeeFinderService.find(circles);
		// Put them on response objects to send
		List<ResponseObject> responseObjects = employeeFinderService.obtainResponseObjects(mymap);
		// Remove redundancies
		List<SelectedPeople> selectedPeople = employeeFinderService.removeDuplicates(mymap);
		// Send mail to selected people and LOG information in the db
		boolean isMailSent = mailerService.sendMail(frontEndObjects.getMessage().getSubject(),
				frontEndObjects.getMessage().getContent(), selectedPeople);
		//mailerService.sendText(frontEndObjects.getMessage().getContent());
		if (isMailSent) {
			String status = "sent";
			logService.LogInfoFromClientSide(circles);
			logService.logAllInfo(frontEndObjects.getMessage().getSubject(), frontEndObjects.getMessage().getContent(),
					status);
			logService.logDependentTables(circles, selectedPeople);
		} else {
			String status = "failed";
			logService.LogInfoFromClientSide(circles);
			logService.logAllInfo(frontEndObjects.getMessage().getSubject(), frontEndObjects.getMessage().getContent(),
					status);
			logService.logDependentTables(circles, selectedPeople);
		}
		return responseObjects;
	}
}
