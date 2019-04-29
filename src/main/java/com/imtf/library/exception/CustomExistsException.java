package com.imtf.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Record already exists")
public class CustomExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomExistsException(String message) {
		super(message);
	}

	public CustomExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}
