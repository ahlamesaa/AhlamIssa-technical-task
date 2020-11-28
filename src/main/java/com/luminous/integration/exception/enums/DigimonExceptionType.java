package com.luminous.integration.exception.enums;

/**
 * 
 * @author Ahlam-issa
 *
 */
public enum DigimonExceptionType {
	BAD_REQUEST("Bad Request"), INVALID_NAME("The name null or Empty ."), INVALID_LEVEL("The level null or Empty ."),
	EMPTY_RESULT_BY_NAME("The level is not exisit on the database ."),
	EMPTY_RESULT_BY_LEVEL("The level is not exisit on the database .");

	private String message;

	private DigimonExceptionType(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
