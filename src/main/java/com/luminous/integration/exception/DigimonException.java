package com.luminous.integration.exception;

import com.luminous.integration.exception.enums.DigimonExceptionType;

/**
 * 
 * @author Ahlam-issa
 *
 */
public class DigimonException extends Exception {
	private DigimonExceptionType type;

	public DigimonException(String message, DigimonExceptionType type) {
		super(message);
		this.type = type;
	}

	public DigimonException(String message, DigimonExceptionType type, Throwable cause) {
		super(message, cause);
		this.type = type;
	}

	public DigimonException(String message, Throwable cause) {
		super(message, cause);

	}

	public DigimonException(String message) {
		super(message);

	}

	public DigimonException(Throwable cause) {
		super(cause);

	}

	public DigimonExceptionType getType() {
		return type;
	}

}
