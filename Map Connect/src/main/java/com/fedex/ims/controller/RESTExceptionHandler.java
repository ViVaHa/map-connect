package com.fedex.ims.controller;

import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fedex.ims.response.dto.GenericExceptionDTO;
import com.fedex.ims.response.dto.ValidationErrorDTO;

/**
 * General error handler for the application.
 */
@ControllerAdvice
public class RESTExceptionHandler {

	private static final Logger LOG = LogManager.getLogger(RESTExceptionHandler.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	public RESTExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public GenericExceptionDTO genericExceptionHandler(Exception ex) {
		return new GenericExceptionDTO(ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ValidationErrorDTO processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		return processFieldErrors(fieldErrors);
	}

	private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
		ValidationErrorDTO dto = new ValidationErrorDTO();

		for (FieldError fieldError : fieldErrors) {
			String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
			dto.addFieldError(fieldError.getField(), localizedErrorMessage);
		}

		return dto;
	}

	private String resolveLocalizedErrorMessage(FieldError fieldError) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
		/*
		 * if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
		 * String[] fieldErrorCodes = fieldError.getCodes();
		 * localizedErrorMessage = fieldErrorCodes[0]; }
		 */
		LOG.info(localizedErrorMessage);
		return localizedErrorMessage;
	}
	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public GenericExceptionDTO dataExceptionHandler(Exception ex) {
		return new GenericExceptionDTO(ex.getMessage());
	}
}