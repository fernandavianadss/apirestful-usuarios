package br.com.fernandaviana.apirestful.services.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		super("Resource bot found. Id "+id);
	}

}
