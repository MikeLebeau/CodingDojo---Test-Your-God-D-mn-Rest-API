package com.oodrive.codingdojoapitest.db;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	public NotFoundException(String message) {
		super(message);
	}

	public static NotFoundException create(Class<?> type, Object id) {
		return create(type.getName(), id);
	}

	public static NotFoundException create(String type, Object id) {
		return new NotFoundException(String.format("Entity %s with id %s was not found", type, id));
	}
}
