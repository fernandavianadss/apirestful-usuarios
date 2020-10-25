package br.com.fernandaviana.apirestful.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Integer status, String mensagem) {
		super(status, mensagem);
	}

	public void addError(String messagem) {
		errors.add(new FieldMessage(messagem));
	}
}
