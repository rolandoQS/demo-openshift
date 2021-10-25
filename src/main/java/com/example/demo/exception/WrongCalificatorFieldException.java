package com.example.demo.exception;

import java.util.Collection;

public class WrongCalificatorFieldException extends ApiException {

	private final String current;
	private final Collection<String> pool;

	public WrongCalificatorFieldException(String current, Collection<String> pool) {
		super("422", "El valor "+current+" no está entre los aceptados: "+String.join(",", pool), 422);
		this.current = current;
		this.pool = pool;
	}

	public String getCurrent() {
		return current;
	}

	public Collection<String> getPool() {
		return pool;
	}
}
