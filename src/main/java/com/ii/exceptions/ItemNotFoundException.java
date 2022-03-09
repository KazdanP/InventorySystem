package com.ii.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No item exists with that ID")
public class ItemNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 502826761347257534L;
	
	public ItemNotFoundException(String msg) {
		super(msg);
	}

}