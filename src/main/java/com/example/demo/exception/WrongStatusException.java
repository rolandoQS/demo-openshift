package com.example.demo.exception;

import java.util.Collection;

public class WrongStatusException extends ApiException {

	private final String current;
	private final Collection<String> pool;

	public WrongStatusException(String entity, Integer id, String current, Collection<String> pool) {
		super("422", "La entidad "+entity+" esta en un estado incorrecto ("+current+") no se puede ejecutar la acción. Los estados aceptados son:"+String.join(",", pool), 422);
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
