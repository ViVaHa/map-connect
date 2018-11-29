package com.fedex.ims.response.dto;

public class GenericExceptionDTO {
	private String error;
	
	public GenericExceptionDTO(String error){
		this.error=error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
